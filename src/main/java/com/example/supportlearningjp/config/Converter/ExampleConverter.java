package com.example.supportlearningjp.config.Converter;

import com.example.supportlearningjp.dto.ExampleDto;
import com.example.supportlearningjp.dto.MeaningDetailDto;
import com.example.supportlearningjp.model.ExampleDetail;
import com.example.supportlearningjp.model.ExampleDetailId;
import com.example.supportlearningjp.model.MeaningDetailId;
import org.modelmapper.AbstractConverter;

public class ExampleConverter  extends AbstractConverter<ExampleDto, ExampleDetail> {
    @Override
    protected ExampleDetail convert(ExampleDto source) {
        ExampleDetail exampleDetail=new ExampleDetail();
       ExampleDetailId exampleDetailId=new ExampleDetailId();
       exampleDetailId.setSentence(source.getSentence());
        exampleDetail.setExampleDetailId(exampleDetailId);
        exampleDetail.setMeaning(source.getMeaning());
       return exampleDetail;
    }
}
