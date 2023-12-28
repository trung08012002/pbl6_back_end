package com.example.supportlearningjp.dto.type;

import lombok.Data;

@Data
public class FormToken {
  private String accessToken;
  private String refreshToken;
}
