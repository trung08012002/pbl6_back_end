package com.example.supportlearningjp.service;

import com.example.supportlearningjp.dto.WordDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface WordJpService {
  WordDto createWordJp(WordDto wordDto);
  List<WordDto> getWordsDto(String name);
  WordDto getFirstWordOfSentence(String word);
  WordDto searchWordByKanjiAndHiragana(String kanji,String hiragana);

  void deleteWordById(String id);
}
