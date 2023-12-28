package com.example.supportlearningjp.controller;


import com.atilika.kuromoji.ipadic.Tokenizer;

import com.example.supportlearningjp.dto.MeaningDetailDto;
import com.example.supportlearningjp.dto.WordDto;

import com.example.supportlearningjp.dto.WordMeaningDto;
import com.example.supportlearningjp.exception.CustomError;
import com.example.supportlearningjp.service.WordJpService;
import com.example.supportlearningjp.util.TinySegments.TinySegmenter;
import com.example.supportlearningjp.util.Translator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/words")
public class WordController {

    private Tokenizer _tokenizer;
    private WordJpService _wordJpService;

    private  TinySegmenter _tinySegmenter;

    @Autowired
    WordController(Tokenizer tokenizer,
                   WordJpService wordJpService,
                   TinySegmenter tinySegmenter
    ) {
        _tokenizer = tokenizer;
        _wordJpService = wordJpService;
        _tinySegmenter=tinySegmenter;
    }
    @GetMapping("/google-translate")
    private static String translate(@RequestParam  String word) throws IOException {
        try {
            return Translator.translate("jp","vn",word);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        return "";
    }
    @GetMapping("/{word}")
    public ResponseEntity<List<WordMeaningDto>> searchWord(@PathVariable("word") String word) {
       List<WordDto> list=_wordJpService.getWordsDto(word);
       List<WordMeaningDto> listWordMeaningDto=list.stream().map(wordDto -> new WordMeaningDto(wordDto.getId(),wordDto.getTu(),wordDto.getHiragana(),
               wordDto.getDetailWords().stream().flatMap(detailWordDto ->
               detailWordDto.getMeaningDetails().stream().map(MeaningDetailDto::getMeaningDetail)).toList())).toList();
       return ResponseEntity.ok(listWordMeaningDto);

    }
    @GetMapping("/searchWord")
    public ResponseEntity<WordDto> searchWordByTuAndHiragana(@RequestParam String word) {
        String[] list=word.split("&");
        if(list.length!=2){
            throw new CustomError("parameter is not suitable");
        }
        String kanji=list[0];
        String hiragana=list[1];
        WordDto wordDto=_wordJpService.searchWordByKanjiAndHiragana(kanji,hiragana);
        if(wordDto==null)
        {
           throw new EmptyResultDataAccessException("No word found", 0);
        }
        return ResponseEntity.ok(wordDto);
    }


    @GetMapping("/getFirstWordOfSentence")
    public ResponseEntity<WordDto> getFirstWordOfSentence(@RequestParam String word) {

        if(word.isEmpty())
        {
            throw new CustomError("No argument word");
        }

        List<String> tokens = null;
        tokens = _tinySegmenter.segment(word);
        WordDto wordDto=null;
        if(tokens.isEmpty()){
            wordDto=_wordJpService.getFirstWordOfSentence(word);
            if(wordDto==null)
            {
                throw new EmptyResultDataAccessException("No word found", 0);
            }
            return ResponseEntity.ok(wordDto);
        }
        else{
            wordDto = _wordJpService.getFirstWordOfSentence(tokens.get(0));
            if(wordDto==null)
            {
                throw new EmptyResultDataAccessException("No word found", 0);
            }
            return ResponseEntity.ok(wordDto);
        }
    }
    @PostMapping("")
    public ResponseEntity<WordDto> createWord(@RequestBody WordDto word) {
        return ResponseEntity.ok(_wordJpService.createWordJp(word));
    }
    @DeleteMapping("")
    public ResponseEntity<Boolean> deleteWord(@RequestParam String idWord)
    {


        _wordJpService.deleteWordById(idWord);
        return  ResponseEntity.ok(true);
    }
    @GetMapping("/search")
    public ResponseEntity<List<WordDto>> searchWords(@RequestParam String word) {

        if(word.isEmpty())
        {
            throw new CustomError("No argument word");
        }

        List<String> tokens = null;
        tokens = _tinySegmenter.segment(word);
        List<WordDto> wordDtoList=null;
        if(tokens.isEmpty()){
            wordDtoList=_wordJpService.getWordsDto(word);
            if(wordDtoList.isEmpty())
            {
                throw new EmptyResultDataAccessException("No word found", 0);
            }
            return ResponseEntity.ok(wordDtoList);
        }
        else{

               wordDtoList = _wordJpService.getWordsDto(tokens.get(0));
               if(wordDtoList.isEmpty())
               {
                   wordDtoList=_wordJpService.getWordsDto(word);
                   if(wordDtoList.isEmpty())
                   {
                       throw new EmptyResultDataAccessException("No word found", 0);
                   }
               }
               return ResponseEntity.ok(wordDtoList);

        }

        //        List<String> words = new ArrayList<>();
//        StringBuilder tempString = new StringBuilder();
//        boolean checkHasSupportWord=false;
//        if(word.isEmpty())
//        {
//            throw new CustomError("No argument word");
//        }
//        for (Token token : _tokenizer.tokenize(word)) {
//
//            if (!token.getPartOfSpeechLevel1().startsWith("助詞")) {
//
//                tempString.append(token.getSurface());
//            } else {
//                if(!checkHasSupportWord)
//                {
//                    checkHasSupportWord=true;
//                }
//                words.add(tempString.toString());
//                int length = tempString.length();
//                tempString.delete(0, length);
//            }
//
//        }
//        if(!checkHasSupportWord)
//        {
//            words.add(tempString.toString());
//        }
//        if(words.isEmpty())
//        {
//            throw new CustomError("No Word");
//        }
    }
}
