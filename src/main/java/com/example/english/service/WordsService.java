package com.example.english.service;
import com.example.english.entity.Words;
import com.example.english.repo.WordsRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class WordsService {

    private final WordsRepo wordsRepository;
    private final TransactionService transactionService;
    private final UserService userService;

    public WordsService(WordsRepo wordRepository, TransactionService transactionService, UserService userService) {
        this.wordsRepository = wordRepository;
        this.transactionService = transactionService;
        this.userService = userService;
    }

    public List<Words> findWordIdsByUserIdNotInTransactions(String workType,int userId) {
            List<Integer> id = transactionService.findWordIdsByUserIdKnowTrue(userId);
           List<Words> words= wordsRepository.findRandomWordsByWorkTypeAndExcludeWordIds(workType,id);
           return words;
    }

    public void deleteWorldById(int id) {
        wordsRepository.deleteById(id);
    }

    public void createWorldById(Words words) {
        wordsRepository.save(words);
    }

    public List<Words> findWordsKnowTrue(int userId,String workType) {
        List<Integer> id = transactionService.findWordIdsByUserIdKnowTrue(userId);
        List<Words> words= wordsRepository.findWorkByIdsAndByWorkType(id,workType);
        return words;
    }
    public List<Words> findWordsKnowTrue2(int userId) {
        List<Integer> id = transactionService.findWordIdsByUserIdKnowTrue(userId);
        List<Words> words= wordsRepository.findWorkByIds(id);
        return words;
    }

    public List<Words> findWordsKnowFalse(int userId,String workType) {
        List<Integer> id = transactionService.findWordIdsByUserIdKnowFalse(userId);
        List<Words> words= wordsRepository.findWorkByIdsAndByWorkType(id,workType);
        return words;
    }

    public List<String> findRandomWord(String workType) {
        Words words = wordsRepository.findRandomWordByWorkType(workType).orElseThrow();
        List<String> answer = wordsRepository.findRandomMeaningsExcluding(words.getMeanings());
        List<String> question = new ArrayList<>();
        if (words != null && answer != null) {
            question.add(String.valueOf(words.getId()));
            question.add(words.getTerms());
            question.add(words.getMeanings());
            question.addAll(answer);
        } else {

        }
        return question;
    }
    public Boolean questionTest(int wordId,String terms,String meanings,int id){
        Words word=wordsRepository.findWordsById(wordId);
        if(word.getMeanings().equals(meanings)){
            userService.addPoint(id);
            return true;
        }
        return false;
    }



}
