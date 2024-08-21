package com.example.ProcessosJuridicos.dto;

import org.springframework.beans.BeanUtils;

import com.example.ProcessosJuridicos.model.Processo;
import com.example.ProcessosJuridicos.model.Reu;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
@AllArgsConstructor
public class ReuDTO {

  @NotBlank(message = "Nome do réu é obrigatório")
  private String nome;

  private Long processoId;

  public ReuDTO(Reu entity) {

    BeanUtils.copyProperties(entity, this);

  }
}