package com.example.english.service;

import com.example.english.entity.Transaction;
import com.example.english.repo.TransactionRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepo transactionRepository;


    public TransactionService(TransactionRepo transactionRepo) {
        this.transactionRepository = transactionRepo;
    }


    public List<Integer> findWordIdsByUserIdKnowTrue(int userId) {
        return transactionRepository.findWordIdsByUserIdAAndKnowTrue(userId);
    }

    public List<Integer> findWordIdsByUserIdKnowFalse(int userId) {
        return transactionRepository.findWordIdsByUserIdKAndKnowIsFalse(userId);
    }

    public void createTransaction(Transaction transaction){
        transaction.setDate(LocalDateTime.now());
        transactionRepository.save(transaction);
    }
    public int getKnownWordsCountByUserId(int userId) {
        return transactionRepository.countKnownWordsByUserId(userId);
    }

}
