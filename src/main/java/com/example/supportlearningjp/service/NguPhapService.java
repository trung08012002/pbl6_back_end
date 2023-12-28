package com.example.supportlearningjp.service;

import com.example.supportlearningjp.dto.NguPhapDto;

import java.util.List;

public interface NguPhapService {
  List<NguPhapDto> getListNguPhap();
  NguPhapDto createNguPhap(NguPhapDto nguPhapDto);
}
