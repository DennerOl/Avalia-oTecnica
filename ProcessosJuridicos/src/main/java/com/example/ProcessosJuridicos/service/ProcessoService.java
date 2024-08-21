package com.example.ProcessosJuridicos.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.ProcessosJuridicos.dto.ProcessoDTO;
import com.example.ProcessosJuridicos.model.Processo;
import com.example.ProcessosJuridicos.model.Reu;
import com.example.ProcessosJuridicos.repository.ProcessoRepository;
import com.example.ProcessosJuridicos.repository.ReuRepository;
import com.example.ProcessosJuridicos.service.exceptions.EntityNotFoundException;

@Service
public class ProcessoService {

  @Autowired
  private ProcessoRepository processoRepository;

  @Autowired
  private ReuRepository reuRepository;

  @Transactional(readOnly = true)
  public ProcessoDTO buscarPorId(Long id) {
    Processo result = processoRepository.findById(id).orElseThrow(
        () -> new com.example.ProcessosJuridicos.service.exceptions.NotFoundException("Processo não existente"));
    return new ProcessoDTO(result);

  }

  @Transactional()
  public ProcessoDTO saveProcesso(ProcessoDTO dto) {
    if (processoRepository.existsByNumero(dto.getNumero())) {
      throw new RuntimeException("Processo já cadastrado");
    }
    Processo processo = new Processo();
    processo.setNumero(dto.getNumero());

    Processo savedProcesso = processoRepository.save(processo);

    ProcessoDTO savedProcessoDTO = new ProcessoDTO();
    savedProcessoDTO.setNumero(savedProcesso.getNumero());
    return savedProcessoDTO;
  }

  @Transactional(readOnly = true)
  public ProcessoDTO getProcessoByNumero(String numero) {

    Optional<Processo> optionalProcesso = processoRepository.findByProcessNumber(numero);

    Processo processo = optionalProcesso
        .orElseThrow(() -> new EntityNotFoundException("Processo não encontrado com o número: " + numero));

    ProcessoDTO processoDTO = new ProcessoDTO();
    BeanUtils.copyProperties(processo, processoDTO);

    return processoDTO;
  }

  @Transactional(propagation = Propagation.SUPPORTS)
  public void deleteProcesso(String numeroProcesso) {
    processoRepository.deleteByNumero(numeroProcesso);
  }

  @Transactional
  public void adicionarReuAoProcesso(String numero, Reu reu) {
    Optional<Processo> optionalProcesso = processoRepository.findByProcessNumber(numero);
    if (optionalProcesso.isEmpty()) {
      throw new EntityNotFoundException("Processo não encontrado com o número: " + numero);
    }

    Processo processo = optionalProcesso.get();

    reu.setProcesso(processo);

    processo.getReus().add(reu);

    reuRepository.save(reu);
    processoRepository.save(processo);
  }

}