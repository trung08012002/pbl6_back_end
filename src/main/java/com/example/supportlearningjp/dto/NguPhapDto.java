package com.example.supportlearningjp.dto;




import com.example.supportlearningjp.model.Level;
import lombok.Data;

@Data
public class NguPhapDto {
    private Integer id;
    private Level level;
    private String nguPhap;
    private String meaning;
    private String phanLoai;
}
