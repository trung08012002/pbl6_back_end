package com.example.supportlearningjp.controller;

import com.example.supportlearningjp.dto.NguPhapDto;
import com.example.supportlearningjp.model.NguPhap;
import com.example.supportlearningjp.service.NguPhapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/nguphap")
public class NguPhapController {
    @Autowired
    NguPhapService nguPhapService;
    @GetMapping("")
    public ResponseEntity<List<NguPhapDto>> getAllNguPhap()
    {
      List<NguPhapDto> list= nguPhapService.getListNguPhap();

      return ResponseEntity.ok(list);
    }
    @PostMapping("")
    public ResponseEntity<NguPhapDto> createNguPhap(NguPhapDto nguPhapDto)
    {
       NguPhapDto createdNguPhap=nguPhapService.createNguPhap(nguPhapDto);
       return ResponseEntity.ok(createdNguPhap);
    }
}
