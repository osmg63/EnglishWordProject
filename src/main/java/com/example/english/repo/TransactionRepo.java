package com.example.english.repo;

import com.example.english.entity.TransactionWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<TransactionWord, Integer> {
    @Query("SELECT t.word.id FROM TransactionWord t WHERE t.user.id = :userId AND t.know = true")
    List<Integer> findWordIdsByUserIdAAndKnowTrue(int userId);

    @Query("SELECT t.word.id FROM TransactionWord t WHERE t.user.id = :userId AND t.know = false")
    List<Integer> findWordIdsByUserIdKAndKnowIsFalse(int userId);

    @Query("SELECT COUNT(t.word.id) FROM TransactionWord t WHERE t.user.id = :userId AND t.know = true")
    int countKnownWordsByUserId(int userId);

}