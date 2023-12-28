package com.example.supportlearningjp.dto;



import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


import java.util.List;


@Data
public class WordDto {
    private Integer id;

    @NotEmpty(message = "Tu should not be null or empty")
    private String tu;

    private String pronounce;

    private String hiragana;



    List<HanVietDto> hanViets;

    List<DetailWordDto> detailWords;
}
