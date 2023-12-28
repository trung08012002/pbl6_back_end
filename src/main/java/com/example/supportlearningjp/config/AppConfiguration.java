package com.example.supportlearningjp.config;





import com.atilika.kuromoji.ipadic.Tokenizer;
import com.example.supportlearningjp.util.TinySegments.TinySegmenter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Bean
    public Tokenizer getTokenizer(){
        return new Tokenizer();
    }
   @Bean
    public TinySegmenter getTinySegmenter(){
         return  TinySegmenter.getInstance();
    }

}
