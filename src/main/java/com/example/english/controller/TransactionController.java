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



    @PostMapping("/create")
    public ResponseEntity<DtoTransactionWord> createTransaction(@RequestBody DtoTransactionWord transaction){
       DtoTransactionWord data= transactionService.createTransaction(transaction);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }
    @GetMapping("getKnownWordsCountByUserId/{userId}")
    public ResponseEntity<Integer> getKnownWordsCountByUserId(@PathVariable int userId) {
        int count = transactionService.getKnownWordsCountByUserId(userId);
        return ResponseEntity.ok(count);
    }


}
