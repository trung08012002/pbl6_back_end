package com.example.supportlearningjp.service.impl;

import com.example.supportlearningjp.dto.NguPhapDto;
import com.example.supportlearningjp.model.NguPhap;
import com.example.supportlearningjp.repo.NguPhapRepository;
import com.example.supportlearningjp.service.NguPhapService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NguPhapServiceImpl implements NguPhapService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private NguPhapRepository nguPhapRepository;
    @Override
    public List<NguPhapDto> getListNguPhap() {
       List<NguPhapDto> nguPhapDtoList = nguPhapRepository.findAll().stream().map((this::mapToDto)).toList();
       return nguPhapDtoList;
    }

    @Override
    public NguPhapDto createNguPhap(NguPhapDto nguPhapDto) {
       NguPhap nguPhap= mapToEntity(nguPhapDto);
        NguPhap nguPhapCreated=  nguPhapRepository.save(nguPhap);
       return mapToDto(nguPhapCreated);
    }
    private NguPhap mapToEntity(NguPhapDto nguPhapDto)
    {
        NguPhap nguPhap=modelMapper.map(nguPhapDto,NguPhap.class);
        return nguPhap;
    }
    private NguPhapDto mapToDto(NguPhap nguPhap)
    {
        return modelMapper.map(nguPhap,NguPhapDto.class);
    }
}
