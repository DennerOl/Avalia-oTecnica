package com.example.ProcessosJuridicos.dto;

import org.springframework.beans.BeanUtils;

import com.example.ProcessosJuridicos.model.Processo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessoDTO {

  @NotBlank(message = "Número do processo é obrigatório")
  private String numero;

  public ProcessoDTO(Processo entity) {

    BeanUtils.copyProperties(entity, this);

  }
}