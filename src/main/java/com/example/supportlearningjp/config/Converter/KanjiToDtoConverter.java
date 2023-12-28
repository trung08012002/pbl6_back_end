package com.example.supportlearningjp.config.Converter;


import com.example.supportlearningjp.dto.KanjiDto;
import com.example.supportlearningjp.model.HanViet;
import com.example.supportlearningjp.model.Kanji;
import org.modelmapper.AbstractConverter;

public class KanjiToDtoConverter extends AbstractConverter<Kanji, KanjiDto> {

    @Override
    protected KanjiDto convert(Kanji source) {
        KanjiDto kanjiDto=new KanjiDto();
        kanjiDto.setId(source.getId().toString());
        kanjiDto.setAmOn(source.getAmOn().stream().map(amOn -> amOn.getAmOnId().getAmOn()).toList());
        kanjiDto.setAmKun(source.getAmKun().stream().map(amOn -> amOn.getAmKunId().getAmOn()).toList());
        kanjiDto.setNumberOfStroke(source.getNumberOfStroke());
        kanjiDto.setHanTuMeanings(source.getHanTuMeanings().stream().map((hanTuMeaning -> hanTuMeaning.getHanTuMeaningId().getMeaning())).toList());
        kanjiDto.setHanviets(source.getHanViets().stream().map((HanViet::getTu)).toList());
        kanjiDto.setTu(source.getTu());
        return kanjiDto;
    }
}
