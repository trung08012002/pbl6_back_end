package com.example.supportlearningjp.service.impl;

import com.example.supportlearningjp.dto.DetailWordDto;
import com.example.supportlearningjp.dto.MeaningDetailDto;
import com.example.supportlearningjp.dto.WordDto;
import com.example.supportlearningjp.model.*;
import com.example.supportlearningjp.repo.*;
import com.example.supportlearningjp.service.WordJpService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;

@Service
public class WordJpServiceImpl implements WordJpService {

    @Autowired
    private WordJpRepository wordJpRepository;

    @Autowired
    private DetailWordRepository detailWordRepository;

    @Autowired
    private HanVietRepository hanVietRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private MeaningDetailRepository meaningDetailRepository;

    @Autowired
    private NoteDetailRepository noteDetailRepository;
    @Autowired
    private ExampleDetailRepository exampleDetailRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public WordDto createWordJp(WordDto wordDto) {
     WordJP wordJP=mapToEntity(wordDto);

     WordJP newWordJP=wordJpRepository.save(wordJP);

     WordDto wordReponse=mapToDTO(newWordJP);
     return wordReponse;
    }

    @Override
    public List<WordDto> getWordsDto(String name) {
       List<WordJP> wordJPList= wordJpRepository.searchByHiraganaName(name);
       List<WordDto> wordDtos=new ArrayList<>();

       wordJPList.forEach((WordJP wordJp)->wordDtos.add(mapToDTO(wordJp)));
       return wordDtos;
    }

    @Override
    public WordDto getFirstWordOfSentence(String word) {
        WordJP wordJP=wordJpRepository.getFirstWordOfSentence(word);
        WordDto wordDto=mapToDTO(wordJP);
        return wordDto;
    }

    @Override
    public WordDto searchWordByKanjiAndHiragana(String kanji, String hiragana) {
        WordJP wordJP=wordJpRepository.searchWordByKanjiAndHiragana(kanji,hiragana);
        WordDto wordDto=mapToDTO(wordJP);
        return wordDto;
    }

    @Override
    public void deleteWordById(String id) {
       Optional<WordJP> hasWord=wordJpRepository.findById(Integer.parseInt(id));
       if(hasWord.isPresent())
       {
          WordJP word= hasWord.get();

           List<DetailWord> detailWordList= word.getDetailWords();
           detailWordList.forEach((detailWord -> {
              typeRepository.deleteAll(detailWord.getTypeWords());
              meaningDetailRepository.deleteAll(detailWord.getMeaningDetails());
              noteDetailRepository.deleteAll(detailWord.getNoteDetails());
              exampleDetailRepository.deleteAll(detailWord.getExampleDetails());
           }));
           detailWordRepository.deleteAll(detailWordList);

           hanVietRepository.deleteAll(word.getHanViets());
           wordJpRepository.delete(word);
       }

    }

    //5時に駅で母のパンをたべなさいよ
    private WordJP mapToEntity(WordDto wordDto)
    {


      WordJP wordJP=modelMapper.map(wordDto,WordJP.class);
        wordJP.getDetailWords().forEach((DetailWord dw)->{
            dw.setWordJP(wordJP);
            if(dw.getMeaningDetails()!=null)
            {
                dw.getMeaningDetails().forEach((MeaningDetail md)->md.getMeaningDetailId().setDetailWord(dw));
            }
            if(dw.getTypeWords()!=null)
            {
                dw.getTypeWords().forEach((TypeWord tp)->tp.setDetailWords(wordJP.getDetailWords()));
            }
            if(dw.getExampleDetails()!=null){
                dw.getExampleDetails().forEach((ExampleDetail ex)->ex.getExampleDetailId().setDetailWord(dw));
            }
            if(dw.getNoteDetails()!=null){
                dw.getNoteDetails().forEach((NoteDetail nd)->nd.getNoteDetailId().setDetailWord(dw));
            }
        });
        return  wordJP;
    }
    private WordDto mapToDTO(WordJP wordJP)
    {
       WordDto wordDto = modelMapper.map(wordJP,WordDto.class);
       List<DetailWordDto> detailWordDtoList=wordDto.getDetailWords();
       if(detailWordDtoList==null)
       {
           return wordDto;
       }
       for(int i=0;i<detailWordDtoList.size();i++)
       {
           DetailWord detailWord=wordJP.getDetailWords().get(i);
           DetailWordDto detailWordDto=detailWordDtoList.get(i);
           detailWordDto.setIdDetail(detailWord.getId());
          if(detailWord.getMeaningDetails()!=null)
          {
              for(int j=0;j<detailWordDto.getMeaningDetails().size();j++)
              {
                  detailWordDto.getMeaningDetails().get(j).setMeaningDetail(detailWord.getMeaningDetails().get(j).getMeaningDetailId().getMeaningDetail());
              }
          }
           if(detailWord.getExampleDetails()!=null)
           {
               for(int j=0;j<detailWordDto.getExampleDetails().size();j++)
               {
                   detailWordDto.getExampleDetails().get(j).setIdDetail(detailWord.getId());
                   detailWordDto.getExampleDetails().get(j).setMeaning(detailWord.getExampleDetails().get(j).getMeaning());
                   detailWordDto.getExampleDetails().get(j).setSentence(detailWord.getExampleDetails().get(j).getExampleDetailId().getSentence());
               }
           }
           if(detailWordDto.getNoteDetails()!=null){
               for(int j=0;j<detailWordDto.getNoteDetails().size();j++)
               {
                   detailWordDto.getNoteDetails().get(j).setNoteDetail(detailWord.getNoteDetails().get(j).getNoteDetailId().getNote());

               }
           }

           detailWordDtoList.set(i,detailWordDto);
       }
       return  wordDto;
    }
}
