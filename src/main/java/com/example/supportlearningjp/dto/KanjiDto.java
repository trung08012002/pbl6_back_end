package com.example.supportlearningjp.dto;


import lombok.Data;

import java.util.List;

@Data
public class KanjiDto {
    private String id;
    private String tu;
    private Integer numberOfStroke;
    private List<String> hanTuMeanings;
    private List<String> amOn;
    private List<String> amKun;
    private List<String> hanviets;
}
