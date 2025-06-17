package com.example.english.controller;


import com.example.english.dto.DtoQuestion;
import com.example.english.dto.DtoTestGame;
import com.example.english.entity.Words;
import com.example.english.service.WordsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/words")
public class WordsController {

    private final WordsService wordsService;


    @GetMapping("/pdf/{workType}/{id}")
    public ResponseEntity<byte[]> getPdf(@PathVariable String workType, @PathVariable int id) throws Exception {
        byte[] pdfBytes = wordsService.pdfForWord(workType, id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=example.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
    @GetMapping("/findWordIdsByUserIdNotInTransactions/{workType}/{userId}")
    public List<Words> getRandomWordsByWorkTypeAndUserId(@PathVariable String workType, @PathVariable int userId) {
        return wordsService.findWordIdsByUserIdNotInTransactions(workType, userId);
    }
    @GetMapping("/getWordsBydUserIdAndKnowTrue/{userId}/{workType}")
    public List<Words> getWordsBydUserIdAndKnowTrue( @PathVariable int userId,@PathVariable String workType) {
        return wordsService.findWordsKnowTrue(userId,workType);
    }
    @GetMapping("/getWordsBydUserId/{userId}")
    public List<Words> getWordsBydUserId( @PathVariable int userId) {
        return wordsService.findWordsKnowTrue2(userId);
    }
    @GetMapping("/getWordsBydUserIdAndKnowFalse/{userId}/{workType}")
    public List<Words> getWordsBydUserIdAndKnowFalse( @PathVariable int userId,@PathVariable String workType) {
        return wordsService.findWordsKnowFalse(userId,workType);
    }
    @GetMapping("/getRandomWord/{workType}")
    public ResponseEntity<DtoTestGame> getRandomWord(@PathVariable String workType) {
        var result = wordsService.findRandomWord(workType);
        return ResponseEntity.ok(result);
    }
    @PostMapping("/questionTest")
    public Boolean questionTest(@RequestBody DtoQuestion questionDto) {
        int wordId = questionDto.getWordId();
        String meanings = questionDto.getMeanings();
        int id = questionDto.getId();

        return wordsService.questionTest(wordId, meanings, id);
    }
    @PostMapping("/delete/{id}")
    public void deleteWord( @PathVariable int id){
        wordsService.deleteWorldById(id);
    }

    @PostMapping("/create")
    public void createWord(@RequestBody Words words){
        wordsService.createWorld(words);
    }




}
