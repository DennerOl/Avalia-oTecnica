package com.example.ProcessosJuridicos.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProcessosJuridicos.dto.ReuDTO;
import com.example.ProcessosJuridicos.model.Processo;
import com.example.ProcessosJuridicos.model.Reu;
import com.example.ProcessosJuridicos.repository.ProcessoRepository;
import com.example.ProcessosJuridicos.repository.ReuRepository;
import com.example.ProcessosJuridicos.service.exception.NotFoundException;

@Service
public class ReuService {

  @Autowired
  private ReuRepository reuRepository;

  @Autowired
  private ProcessoRepository processoRepository;



  public Set<ReuDTO> addReusToProcesso(String numeroProcesso, Set<ReuDTO> reusDTO) {
    
    Processo processo = processoRepository.findByNumero(numeroProcesso)
        .orElseThrow(() -> new NotFoundException("Processo não encontrado com número: " + numeroProcesso));

    Set<Reu> reusSalvos = new HashSet<>();

    for (ReuDTO dto : reusDTO) {
        Reu reu = reuRepository.findByCpf(dto.getCpf())
            .orElseGet(() -> {
                Reu newReu = new Reu();
                newReu.setNome(dto.getNome());
                newReu.setCpf(dto.getCpf());
                return reuRepository.save(newReu);
            });

        reusSalvos.add(reu);
    }

    processo.getReus().addAll(reusSalvos);

    processoRepository.save(processo);

    return reusSalvos.stream().map(reu -> {
        ReuDTO dto = new ReuDTO();
        dto.setId(reu.getId());
        dto.setNome(reu.getNome());
        dto.setCpf(reu.getCpf());
        return dto;
    }).collect(Collectors.toSet());
}

public Set<ReuDTO> findAll() {
    List<Reu> reus = reuRepository.findAll();
    return reus.stream()
               .map(reu -> new ReuDTO(reu)) 
               .collect(Collectors.toSet()); 
}



}
