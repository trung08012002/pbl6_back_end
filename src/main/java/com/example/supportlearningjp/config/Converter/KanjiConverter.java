package com.example.supportlearningjp.config.Converter;

import com.example.supportlearningjp.dto.ExampleDto;
import com.example.supportlearningjp.dto.KanjiDto;
import com.example.supportlearningjp.model.*;
import org.modelmapper.AbstractConverter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class KanjiConverter  extends AbstractConverter<KanjiDto, Kanji> {

    @Override
    protected Kanji convert(KanjiDto source) {
        Kanji kanji=new Kanji();
        Set<AmKun> amKunSet=
        source.getAmKun().stream().map((amKun)->{
            AmKun amKunTemp=new AmKun();
            AmKunId amKunId=new AmKunId();
            amKunId.setKanji(kanji);
            amKunId.setAmOn(amKun);
            amKunTemp.setAmKunId(amKunId);
            return amKunTemp;
        }).collect(Collectors.toSet());
        kanji.setAmKun(amKunSet);
        Set<AmOn> amOnSet=source.getAmOn().stream().map((amOn)->{
            AmOn amOnTemp=new AmOn();
            AmOnId amOnId=new AmOnId();
            amOnId.setKanji(kanji);
            amOnId.setAmOn(amOn);
            amOnTemp.setAmOnId(amOnId);
            return  amOnTemp;
        }).collect(Collectors.toSet());
        kanji.setAmOn(amOnSet);
        Set<HanViet> hanViets=source.getHanviets().stream().map((hanviet)->{
            HanViet hanVietTemp=new HanViet();
            hanVietTemp.setTu(hanviet);
            return hanVietTemp;
        }).collect(Collectors.toSet());
        kanji.setHanViets(hanViets);
        kanji.setTu(source.getTu());
        kanji.setNumberOfStroke(source.getNumberOfStroke());
        List<HanTuMeaning> hanTuMeaningList= source.getHanTuMeanings().stream().map((meaning)->{
            HanTuMeaning hanTuMeaning=new HanTuMeaning();
            HanTuMeaningId hanTuMeaningId=new HanTuMeaningId();
            hanTuMeaningId.setKanji(kanji);
            hanTuMeaningId.setMeaning(meaning);
            hanTuMeaning.setHanTuMeaningId(hanTuMeaningId);
            return hanTuMeaning;
        }).toList();
        kanji.setHanTuMeanings(hanTuMeaningList);
        return kanji;
    }
}
