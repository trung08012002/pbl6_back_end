package com.example.supportlearningjp.service.impl;

import com.example.supportlearningjp.dto.KanjiDto;
import com.example.supportlearningjp.model.Kanji;
import com.example.supportlearningjp.repo.HanTuRepository;
import com.example.supportlearningjp.service.KanjiService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class KanjiServiceImpl implements KanjiService {
    @Autowired
    private HanTuRepository hanTuRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<KanjiDto> getKanjiWord(String[] kanjis) {
        List<Kanji> kanjiList=hanTuRepository.findByTuIn(kanjis);
        return kanjiList.stream().map(this::mapToDto).toList();
    }

    @Override
    public KanjiDto createKanji(KanjiDto kanjiDto) {
        Kanji kanji=mapToEntity(kanjiDto);
       return mapToDto(hanTuRepository.save(kanji));
    }
    private  Kanji mapToEntity(KanjiDto kanjiDto)
    {
        Kanji kanji= modelMapper.map(kanjiDto,Kanji.class);
        return kanji;
    }
    private KanjiDto mapToDto(Kanji kanji){
        KanjiDto kanjiDto= modelMapper.map(kanji,KanjiDto.class);
        return kanjiDto;
    }
}
