package com.example.supportlearningjp.config;

import com.example.supportlearningjp.config.Converter.*;

import org.modelmapper.ModelMapper;

import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ConvertConfiguration {
    @Bean
    public MeaningDetailConverter meaningDetailConverter(){
        return new MeaningDetailConverter();
    }
    @Bean
    public ExampleConverter exampleConverter()
    {
        return new ExampleConverter();
    }
    @Bean
    public TypeWordConverter typeWordConverter(){return new TypeWordConverter();}
    @Bean
    public NoteDetailConverter noteDetailConverter(){return new NoteDetailConverter();}
    @Bean
    public KanjiConverter kanjiConverter(){
        return  new KanjiConverter();
    }
    @Bean
    public KanjiToDtoConverter KanjiToDtoConverter(){
        return new KanjiToDtoConverter();
    }

    @Bean
    public  NguPhapConverter nguPhapConverter(){ return  new NguPhapConverter();}

    @Bean
    public NguPhapEntityToNguPhapDtoConverter nguPhapEntityToNguPhapDtoConverter()
    {
        return new NguPhapEntityToNguPhapDtoConverter();
    }
    @Autowired
    @Bean
    public ModelMapper modelMapper(NguPhapEntityToNguPhapDtoConverter nguPhapEntityToNguPhapDtoConverter,NguPhapConverter nguPhapConverter,KanjiToDtoConverter kanjiToDtoConverter,KanjiConverter kanjiConverter, MeaningDetailConverter meaningDetailConverter, ExampleConverter exampleConverter, TypeWordConverter typeWordConverter, NoteDetailConverter noteDetailConverter)
    {
        ModelMapper modelMapper= new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.addConverter(meaningDetailConverter);
        modelMapper.addConverter(exampleConverter);
        modelMapper.addConverter(typeWordConverter);
        modelMapper.addConverter(noteDetailConverter);
        modelMapper.addConverter(kanjiConverter);
        modelMapper.addConverter(kanjiToDtoConverter);
        modelMapper.addConverter(nguPhapConverter);
        modelMapper.addConverter(nguPhapEntityToNguPhapDtoConverter);
        return modelMapper;
    }
}
