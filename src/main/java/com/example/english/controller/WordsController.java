package com.example.english.controller;


import com.example.english.dto.QuestionDto;
import com.example.english.entity.Words;
import com.example.english.service.WordsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/words")
public class WordsController {

    private final WordsService wordsService;


    public WordsController(WordsService wordsService) {
        this.wordsService = wordsService;
    }

   @PostMapping("/delete")
   public void deleteWord( @PathVariable int id){
        wordsService.deleteWorldById(id);
   }
   @PostMapping("/create")
    public void createWord(@RequestBody Words words){
        wordsService.createWorldById(words);
    }

    @GetMapping("/{workType}/{userId}")
    public List<Words> getRandomWordsByWorkTypeAndUserId(@PathVariable String workType, @PathVariable int userId) {
        return wordsService.findWordIdsByUserIdNotInTransactions(workType, userId);
    }
    @GetMapping("/true/{userId}/{workType}")
    public List<Words> getWordsBydUserIdAndKnowTrue( @PathVariable int userId,@PathVariable String workType) {
        return wordsService.findWordsKnowTrue(userId,workType);
    }
    @GetMapping("/{userId}")
    public List<Words> getWordsBydUserId( @PathVariable int userId) {
        return wordsService.findWordsKnowTrue2(userId);
    }
    @GetMapping("/false/{userId}/{workType}")
    public List<Words> getWordsBydUserIdAndKnowFalse( @PathVariable int userId,@PathVariable String workType) {
        return wordsService.findWordsKnowFalse(userId,workType);
    }
    @GetMapping("/random/{workType}")
    public ResponseEntity<List<String>> getRandomWord(@PathVariable String workType) {
        List<String> result = wordsService.findRandomWord(workType);
        return ResponseEntity.ok(result);
    }
    @PostMapping("/question")
    public Boolean questionTest(@RequestBody QuestionDto questionDto) {
        int wordId = questionDto.getWordId();
        String terms = questionDto.getTerms();
        String meanings = questionDto.getMeanings();
        int id = questionDto.getId();

        return wordsService.questionTest(wordId,terms, meanings, id);
    }
}
