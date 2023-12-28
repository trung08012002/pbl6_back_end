package com.example.supportlearningjp.service;

import com.example.supportlearningjp.model.Katakana;

import java.util.List;

public interface KatakanaService {
    List<Katakana> getListKatakana();

    Katakana createKatakana(Katakana katakana);
}
