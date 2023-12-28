package com.example.supportlearningjp.service;

import com.example.supportlearningjp.dto.KanjiDto;
import com.example.supportlearningjp.model.Kanji;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface KanjiService {
    List<KanjiDto> getKanjiWord(String[] kanjis);
    KanjiDto createKanji(KanjiDto kanji);


}
