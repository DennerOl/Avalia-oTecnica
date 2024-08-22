package com.example.ProcessosJuridicos.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.ProcessosJuridicos.dto.ProcessoDTO;
import com.example.ProcessosJuridicos.dto.ReuDTO;
import com.example.ProcessosJuridicos.model.Processo;
import com.example.ProcessosJuridicos.model.Reu;
import com.example.ProcessosJuridicos.repository.ProcessoRepository;
import com.example.ProcessosJuridicos.repository.ReuRepository;
import com.example.ProcessosJuridicos.service.exception.NotFoundException;
import com.example.ProcessosJuridicos.service.exception.ProcessoDuplicadoException;

@Service
public class ProcessoService {

  @Autowired
  private ProcessoRepository repository;

  @Autowired
    private ReuRepository reuRepository;

 

  @Transactional
  public ProcessoDTO insert(ProcessoDTO dto) {
      if (repository.existsByNumero(dto.getNumero())) {
          throw new ProcessoDuplicadoException("Número de processo já existe.");
      }
        Processo entity = new Processo();
        BeanUtils.copyProperties(dto, entity);

        if (dto.getReus() != null) {
            Set<Reu> reus = new HashSet<>();
            for (ReuDTO reuDTO : dto.getReus()) {
                Reu reu = reuRepository.findByCpf(reuDTO.getCpf())
                        .orElseGet(() -> {
                          
                            Reu newReu = new Reu();
                            BeanUtils.copyProperties(reuDTO, newReu);
                            return reuRepository.save(newReu); 
                        });

                reus.add(reu);
            }

            entity.setReus(reus);
        }

        entity = repository.save(entity);

        return new ProcessoDTO(entity);
    }





  @Transactional(readOnly = true)
  public ProcessoDTO getProcessoByNumero(String numero) {

    Processo processo = repository.findByNumero(numero)
    .orElseThrow(() -> new NotFoundException("Processo não encontrado com o número: " + numero));

ProcessoDTO processoDTO = new ProcessoDTO();

BeanUtils.copyProperties(processo, processoDTO);

// Converter a coleção de Reus para DTOs, se não for nula
if (processo.getReus() != null) {
    Set<ReuDTO> reusDTO = processo.getReus().stream()
        .map(reu -> new ReuDTO(reu.getId(), reu.getNome(), reu.getCpf()))
        .collect(Collectors.toSet());
    processoDTO.setReus(reusDTO);
}

return processoDTO;
}

  @Transactional(propagation = Propagation.SUPPORTS)
  public void delete(String numero) {
    if (!repository.existsByNumero(numero)) {
      throw new NotFoundException("Processo não existente");
    }

    else {
      repository.deleteByNumero(numero);
    }
  }

}