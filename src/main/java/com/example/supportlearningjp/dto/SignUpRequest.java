package com.example.supportlearningjp.dto;

import lombok.Data;

@Data
public class SignUpRequest {
 private String email;
 private String password;
 private Integer idRole;
}
