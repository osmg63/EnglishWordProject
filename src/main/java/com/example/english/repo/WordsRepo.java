package com.example.english.repo;


import com.example.english.entity.Words;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WordsRepo extends JpaRepository<Words, Integer> {
    @Query(value = "SELECT * FROM words WHERE work_type = :workType AND id NOT IN (:wordIds) ORDER BY RANDOM() LIMIT 20", nativeQuery = true)
    List<Words> findRandomWordsByWorkTypeAndExcludeWordIds( String workType,  List<Integer> wordIds);

    @Query(value = "SELECT * FROM words WHERE work_type = :workType ORDER BY RANDOM() LIMIT 20", nativeQuery = true)
    List<Words> findWithoutIds( String workType);


    @Query("SELECT w FROM Words w WHERE w  IN :wordIds  AND w.workType = :workType ORDER BY w.id")
    List<Words> findWorkByIdsAndByWorkType(List<Integer> wordIds,String workType);

    @Query("SELECT w FROM Words w WHERE w  IN :wordIds  ORDER BY w.id")
    List<Words> findWorkByIds(List<Integer> wordIds);

    @Query(value = "SELECT * FROM words WHERE work_type = ?1 ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Optional<Words> findRandomWordByWorkType(String workType);



    @Query(value = "SELECT meanings FROM words WHERE meanings <> ?1 ORDER BY RANDOM() LIMIT 3", nativeQuery = true)
    List<String> findRandomMeaningsExcluding(String correctMeaning);
    Words findWordsByTerms(String string);

    Words findWordsById(int  id);
}

