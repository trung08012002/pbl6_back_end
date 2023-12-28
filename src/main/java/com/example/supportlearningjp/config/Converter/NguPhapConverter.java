package com.example.supportlearningjp.config.Converter;

import com.example.supportlearningjp.dto.NguPhapDto;
import com.example.supportlearningjp.model.NguPhap;
import org.modelmapper.AbstractConverter;

public class NguPhapConverter extends AbstractConverter<NguPhapDto, NguPhap> {
    @Override
    protected NguPhap convert(NguPhapDto source) {
       NguPhap nguphapEntity=new NguPhap();
       nguphapEntity.setId(source.getId());
       nguphapEntity.setNguphap(source.getNguPhap());
       nguphapEntity.setMeaning(source.getMeaning());
       nguphapEntity.setLevel(source.getLevel());
       return nguphapEntity;
    }
}
