package com.example.english.repo;


import com.example.english.entity.Words;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WordsRepo extends JpaRepository<Words, Integer> {
    @Query("SELECT w FROM Words w WHERE w.workType = :workType AND w.id NOT IN :wordIds ORDER BY RAND() LIMIT 20")
    List<Words> findRandomWordsByWorkTypeAndExcludeWordIds( String workType,  List<Integer> wordIds);
    @Query("SELECT w FROM Words w WHERE w  IN :wordIds  AND w.workType = :workType ORDER BY w.id")
    List<Words> findWorkByIdsAndByWorkType(List<Integer> wordIds,String workType);
    @Query("SELECT w FROM Words w WHERE w  IN :wordIds  ORDER BY w.id")
    List<Words> findWorkByIds(List<Integer> wordIds);

    @Query(value = "SELECT w FROM Words w WHERE w.workType = ?1 ORDER BY RAND() LIMIT 1")
    Optional<Words> findRandomWordByWorkType(String workType);
    @Query(value = "SELECT w.meanings FROM Words w WHERE w.meanings <> ?1 ORDER BY RAND() LIMIT 3")
    List<String> findRandomMeaningsExcluding(String correctMeaning);
    Words findWordsByTerms(String string);

    Words findWordsById(int  id);
}

