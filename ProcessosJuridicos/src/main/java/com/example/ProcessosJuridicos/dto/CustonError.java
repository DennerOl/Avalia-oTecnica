package com.example.ProcessosJuridicos.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustonError {

  private Instant timestamp;
  private Integer status;
  private String error;
  private String path;

}
