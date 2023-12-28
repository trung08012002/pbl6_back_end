package com.example.supportlearningjp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class WordMeaningDto {
    private Integer id;
    private String tu;
    private String hiragana;
    private List<String> meaningDetails;


}
