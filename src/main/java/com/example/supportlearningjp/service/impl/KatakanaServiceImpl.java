package com.example.supportlearningjp.service.impl;

import com.example.supportlearningjp.model.Katakana;
import com.example.supportlearningjp.repo.KatakanaRepository;
import com.example.supportlearningjp.service.KatakanaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KatakanaServiceImpl implements KatakanaService {

    @Autowired
    private KatakanaRepository katakanaRepository;
    @Override
    public List<Katakana> getListKatakana() {
        List<Katakana> katakanaList=katakanaRepository.findAll();
        return katakanaList;
    }

    @Override
    public Katakana createKatakana(Katakana katakana) {
         Katakana createdKatakana = katakanaRepository.save(katakana);
         return createdKatakana;
    }
}
