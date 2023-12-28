package com.example.supportlearningjp.repo;

import com.example.supportlearningjp.model.Kanji;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HanTuRepository extends JpaRepository<Kanji,Integer> {

    @Query(value="SELECT * FROM kanji WHERE tu in :kanjis",
    nativeQuery = true
    )
    List<Kanji> findByTuIn(String[] kanjis);

}
