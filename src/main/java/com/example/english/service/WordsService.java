package com.example.english.service;

import com.example.english.dto.DtoTestGame;
import com.example.english.entity.Words;
import com.example.english.exception.BaseException;
import com.example.english.exception.ErrorMessage;
import com.example.english.exception.MessageType;
import com.example.english.repo.WordsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class WordsService {

    private final WordsRepo wordsRepository;
    private final TransactionService transactionService;
    private final UserService userService;



    public List<Words> findWordIdsByUserIdNotInTransactions(String workType, int userId) {
        try {
            List<Integer> id = transactionService.findWordIdsByUserIdKnowTrue(userId);
            List<Words> words = wordsRepository.findRandomWordsByWorkTypeAndExcludeWordIds(workType, id);
            return words;
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, e.getMessage()));
        }
    }

    public void deleteWorldById(int id) {
        try {
            wordsRepository.deleteById(id);
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.DElETE_FAILED, e.getMessage()));
        }

    }

    public void createWorldById(Words words) {
        try {
            wordsRepository.save(words);
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.RECORD_FAILED, e.getMessage()));
        }
    }

    public List<Words> findWordsKnowTrue(int userId, String workType) {
        try {
            List<Integer> id = transactionService.findWordIdsByUserIdKnowTrue(userId);
            List<Words> words = wordsRepository.findWorkByIdsAndByWorkType(id, workType);
            return words;
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, e.getMessage()));
        }


    }

    public List<Words> findWordsKnowTrue2(int userId) {

        try {
            List<Integer> id = transactionService.findWordIdsByUserIdKnowTrue(userId);
            List<Words> words = wordsRepository.findWorkByIds(id);
            return words;
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, e.getMessage()));
        }

    }

    public List<Words> findWordsKnowFalse(int userId, String workType) {
        try {
            List<Integer> id = transactionService.findWordIdsByUserIdKnowFalse(userId);
            List<Words> words = wordsRepository.findWorkByIdsAndByWorkType(id, workType);
            return words;
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, e.getMessage()));
        }


    }

    public DtoTestGame findRandomWord(String workType) {
        try {
            Optional<Words> words = wordsRepository.findRandomWordByWorkType(workType);
            List<String> answer = wordsRepository.findRandomMeaningsExcluding(words.get().getMeanings());
            if (answer == null || words.isEmpty()) {
                throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_FOUND));
            }
            DtoTestGame testGameResponseDto = new DtoTestGame();
            testGameResponseDto.setId(words.get().getId());
            testGameResponseDto.setTerm(words.get().getTerms());
            testGameResponseDto.setWord1(words.get().getMeanings());
            testGameResponseDto.setWord2(answer.get(0));
            testGameResponseDto.setWord3(answer.get(1));
            testGameResponseDto.setWord4(answer.get(2));
            return testGameResponseDto;
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_FOUND, e.getMessage()));
        }

    }

    public Boolean questionTest(int wordId, String meanings, int id) {
        try {
            Words word = wordsRepository.findWordsById(wordId);
            if (word.getMeanings().equals(meanings)) {
                userService.addPoint(id);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, e.getMessage()));
        }


    }


}
