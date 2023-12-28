package com.example.supportlearningjp.service;

import com.example.supportlearningjp.model.Hiragana;
import com.example.supportlearningjp.model.Katakana;

import java.util.List;

public interface HiraganaService {
    List<Hiragana> getListHiragana();

    Hiragana createHiragana(Hiragana hiragana);
    Hiragana updateHiragana(Hiragana hiragana);
    void deleteHiragana(Integer id);
}
