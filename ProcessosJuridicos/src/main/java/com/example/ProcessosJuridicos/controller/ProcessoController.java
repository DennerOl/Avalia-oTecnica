package com.example.ProcessosJuridicos.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.ProcessosJuridicos.dto.ProcessoDTO;
import com.example.ProcessosJuridicos.model.Processo;
import com.example.ProcessosJuridicos.service.ProcessoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/processos")
public class ProcessoController {

  @Autowired
  private ProcessoService service;

  @PostMapping()
  public ResponseEntity<ProcessoDTO> insert(@RequestBody @Valid ProcessoDTO dto) {
    dto = service.saveProcesso(dto);

    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(dto.getNumero()).toUri();
    return ResponseEntity.created(uri).body(dto);
  }

  @GetMapping("/{numeroProcesso}")
  public ResponseEntity<ProcessoDTO> getProcessByNumber(@PathVariable String numeroProcesso) {

    ProcessoDTO dto = service.getProcessoByNumero(numeroProcesso);
    return ResponseEntity.ok(dto);
  }

  @DeleteMapping("/{numeroProcesso}")
  public ResponseEntity<Void> deleteProcesso(@PathVariable String numeroProcesso) {
    service.deleteProcesso(numeroProcesso);
    return ResponseEntity.noContent().build();
  }
}