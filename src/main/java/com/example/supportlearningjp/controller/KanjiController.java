package com.example.supportlearningjp.controller;



import com.example.supportlearningjp.dto.KanjiDto;

import com.example.supportlearningjp.service.KanjiService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class KanjiController {
 @Autowired
 private KanjiService kanjiService;

@PostMapping("/kanji")
    public KanjiDto createKanji(@RequestBody KanjiDto body)
{
    return kanjiService.createKanji(body);
}
@GetMapping("/kanjis")
    public List<KanjiDto> getAllKanjis(@RequestParam String word)
{
 String[] stringList=word.split("");
 return kanjiService.getKanjiWord(stringList);
}
}
