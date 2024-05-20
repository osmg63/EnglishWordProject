package com.example.english.controller;

import com.example.english.entity.Transaction;
import com.example.english.repo.TransactionRepo;
import com.example.english.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;


    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/create")
    public void createTransaction(@RequestBody Transaction transaction){
        transactionService.createTransaction(transaction); ;
    }
    @GetMapping("/{userId}")
    public ResponseEntity<Integer> getKnownWordsCountByUserId(@PathVariable int userId) {
        int count = transactionService.getKnownWordsCountByUserId(userId);
        return ResponseEntity.ok(count);
    }


}
