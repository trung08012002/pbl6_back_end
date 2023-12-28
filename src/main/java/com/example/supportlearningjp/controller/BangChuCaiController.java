package com.example.supportlearningjp.controller;


import com.example.supportlearningjp.model.Hiragana;
import com.example.supportlearningjp.model.Katakana;
import com.example.supportlearningjp.service.HiraganaService;
import com.example.supportlearningjp.service.KatakanaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/bangchucai/")
public class BangChuCaiController {
    @Autowired
    HiraganaService hiraganaService;
    @Autowired
    KatakanaService katakanaService;

    @GetMapping("hiragana")
    List<Hiragana> getListHiragana(){
     List<Hiragana> list= hiraganaService.getListHiragana();
     return  list;
    }
    @GetMapping("katakana")
    List<Katakana> getListKatakana()
    {
        return katakanaService.getListKatakana();
    }
    @PostMapping("hiragana")
    Hiragana createHiragana(@RequestBody Hiragana hiragana)
    {
        return hiraganaService.createHiragana(hiragana);
    }
    @PostMapping("katakana")
    Katakana createKatakana(@RequestBody Katakana katakana)
    {
        return katakanaService.createKatakana(katakana);
    }
    @PutMapping("hiragana")
    Hiragana updateHiragana(@RequestBody Hiragana hiragana) {
        return hiraganaService.updateHiragana(hiragana);
    }
    @DeleteMapping("hiragana")
    ResponseEntity<Boolean> deleteHiragana(@RequestParam Integer id)
    {
         hiraganaService.deleteHiragana(id);
         return ResponseEntity.ok(true);
    }
}
