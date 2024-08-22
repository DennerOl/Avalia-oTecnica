package com.example.ProcessosJuridicos.dto;

import org.springframework.beans.BeanUtils;

import com.example.ProcessosJuridicos.model.Reu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReuDTO {

  private Long id;
  private String nome;
  private String cpf;


  
	public ReuDTO(Reu entity) {
		BeanUtils.copyProperties(entity,this);
	}


}
