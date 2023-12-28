package com.example.supportlearningjp.config.Converter;

import com.example.supportlearningjp.dto.MeaningDetailDto;
import com.example.supportlearningjp.model.MeaningDetail;
import com.example.supportlearningjp.model.MeaningDetailId;
import org.modelmapper.AbstractConverter;

public class MeaningDetailConverter extends AbstractConverter<MeaningDetailDto, MeaningDetail> {
    @Override
    protected MeaningDetail convert(MeaningDetailDto source) {
        MeaningDetail newMeaningDetail=new MeaningDetail();
        MeaningDetailId meaningDetailId=new MeaningDetailId();
        meaningDetailId.setMeaningDetail(source.getMeaningDetail());
        newMeaningDetail.setMeaningDetailId(meaningDetailId);
    return  newMeaningDetail;
    }
}
