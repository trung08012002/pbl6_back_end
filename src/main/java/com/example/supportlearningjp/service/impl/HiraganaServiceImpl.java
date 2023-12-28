package com.example.supportlearningjp.service.impl;

import com.example.supportlearningjp.model.Hiragana;
import com.example.supportlearningjp.repo.HiraganaRepository;
import com.example.supportlearningjp.service.HiraganaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HiraganaServiceImpl implements HiraganaService {

    @Autowired
    HiraganaRepository hiraganaRepository;
    @Override
    public List<Hiragana> getListHiragana() {
         List<Hiragana> hiraganaList=hiraganaRepository.findAll();
         return  hiraganaList;
    }

    @Override
    public Hiragana createHiragana(Hiragana hiragana) {
        Hiragana createdHiragana=hiraganaRepository.save(hiragana);
        return createdHiragana;
    }
    @Override
    public Hiragana updateHiragana(Hiragana hiragana){
        return hiraganaRepository.save(hiragana);
    }

    @Override
    public void deleteHiragana(Integer id) {
          hiraganaRepository.deleteById(id);
    }
}
