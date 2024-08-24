package com.example.ProcessosJuridicos.dto;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.example.ProcessosJuridicos.model.Processo;
import com.example.ProcessosJuridicos.model.Reu;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProcessoDTO {

  private Long id;

  @NotBlank(message = "Número do processo é obrigatório")
  private String numero;

  public ProcessoDTO(Long id, String numero) {
    this.id = id;
    this.numero = numero;
  }

  public ProcessoDTO(Processo entidade) {
    BeanUtils.copyProperties(entidade, this);

    for (Reu r : entidade.getReus()) {
      reus.add(new ReuDTO(r));
    }
  }

  @NotEmpty(message = "Deve conter pelo menos um Reu")
  private Set<ReuDTO> reus = new HashSet<>();

  public Set<ReuDTO> getReus() {
    return reus;
  }

  public void setReus(Set<ReuDTO> reus) {
    this.reus = reus;
  }
}