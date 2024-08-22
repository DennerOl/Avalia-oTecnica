package com.example.ProcessosJuridicos.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ValidationErro extends CustonError {

  private List<FieldMessage> erros = new ArrayList<>();

  public List<FieldMessage> getErros() {
    return erros;
  }

  public void addErros(String fieldName, String message) {
    erros.add(new FieldMessage(fieldName, message));
  }
}
