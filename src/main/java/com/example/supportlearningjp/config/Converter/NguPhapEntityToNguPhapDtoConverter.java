package com.example.supportlearningjp.config.Converter;

import com.example.supportlearningjp.dto.NguPhapDto;
import com.example.supportlearningjp.model.NguPhap;
import org.modelmapper.AbstractConverter;

public class NguPhapEntityToNguPhapDtoConverter extends AbstractConverter<NguPhap, NguPhapDto> {
    @Override
    protected NguPhapDto convert(NguPhap source) {
        NguPhapDto nguPhapDto=new NguPhapDto();
        nguPhapDto.setId(source.getId());
        nguPhapDto.setNguPhap(source.getNguphap());
        nguPhapDto.setLevel(source.getLevel());
        nguPhapDto.setMeaning(source.getMeaning());
        nguPhapDto.setPhanLoai(source.getType().getType());
        return nguPhapDto;
    }
}
