package com.example.english.repo;

import com.example.english.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Integer> {
    @Query("SELECT t.word.id FROM Transaction t WHERE t.user.id = :userId AND t.know = true")
    List<Integer> findWordIdsByUserIdAAndKnowTrue(int userId);
    @Query("SELECT t.word.id FROM Transaction t WHERE t.user.id = :userId AND t.know = false")
    List<Integer> findWordIdsByUserIdKAndKnowIsFalse(int userId);
    @Query("SELECT COUNT(t.word.id) FROM Transaction t WHERE t.user.id = :userId AND t.know = true")
    int countKnownWordsByUserId(int userId);




}
