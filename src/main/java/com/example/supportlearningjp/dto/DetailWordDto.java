package com.example.supportlearningjp.dto;



import lombok.Data;

import java.util.List;

@Data
public class DetailWordDto {
    private  Integer idDetail;

    private List<MeaningDetailDto> meaningDetails;

    private List<ExampleDto> exampleDetails;

    private  List<NoteDetailDto> noteDetails;

    private List<TypeWordDto> typeWords;
}
