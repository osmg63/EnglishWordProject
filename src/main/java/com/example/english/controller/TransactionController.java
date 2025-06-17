package com.example.english.controller;

import com.example.english.dto.DtoTransactionWord;
import com.example.english.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transaction")
public class TransactionController {

    private final TransactionService transactionService;



    @PostMapping("")
    public ResponseEntity<DtoTransactionWord> createTransaction(@RequestBody DtoTransactionWord transaction){
       DtoTransactionWord data= transactionService.createTransaction(transaction);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }
    @GetMapping("/{userId}/known-words/count")
    public ResponseEntity<Integer> getKnownWordsCountByUserId(@PathVariable int userId) {
        int count = transactionService.getKnownWordsCountByUserId(userId);
        return ResponseEntity.ok(count);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DtoTransactionWord> getTransactionById(@PathVariable int id) {
        DtoTransactionWord data=transactionService.getById(id);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DtoTransactionWord> updateTransaction(@PathVariable int id,@RequestBody DtoTransactionWord transaction){
        DtoTransactionWord data= transactionService.updateTransaction(id,transaction);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
    @GetMapping("/{userId}/{wordId}/id")
    public ResponseEntity<Integer> getTransactionIdByUserIdAndWordId(@PathVariable int userId, @PathVariable int wordId) {
        Integer id=transactionService.getTransactionIdByUserIdAndWordId(userId,wordId);
        return ResponseEntity.ok(id);
    }


}
