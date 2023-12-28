package com.example.supportlearningjp.repo;

import com.example.supportlearningjp.model.WordJP;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordJpRepository extends JpaRepository<WordJP,Integer> {
    @Query(value =
//            "SELECT * FROM word_jp WHERE "
//          +"MATCH(hiragana,tu) "+
//            "AGAINST (?1)",
            "SELECT * FROM word_jp WHERE word_jp.tu like :keyword% OR word_jp.hiragana like :keyword%",
            nativeQuery = true
       )
    public List<WordJP> searchByHiraganaName(String keyword);
    @Query(value =
//            "SELECT * FROM word_jp WHERE "
//          +"MATCH(hiragana,tu) "+
//            "AGAINST (?1)",
            "SELECT * FROM word_jp WHERE word_jp.tu like :keyword% OR word_jp.hiragana like :keyword% LIMIT 1",
            nativeQuery = true
    )
    WordJP getFirstWordOfSentence(String keyword);
    @Query(value="SELECT * FROM word_jp WHERE  word_jp.tu=:kanji AND word_jp.hiragana=:hiragana LIMIT 1",nativeQuery = true)
    public WordJP searchWordByKanjiAndHiragana(String kanji,String hiragana);

}
