package com.example.ProcessosJuridicos.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.ProcessosJuridicos.service.ProcessoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/processos")
public class ProcessoController {

  @Autowired
  private ProcessoService service;

  @PostMapping
  public ResponseEntity<ProcessoDTO> inserir(@Valid @RequestBody ProcessoDTO dto) {
    dto = service.insert(dto);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(dto.getId()).toUri();
    return ResponseEntity.created(uri).body(dto);
  }

  @GetMapping(value = "/{numero}")
  public ResponseEntity<ProcessoDTO> getProcessByNumber(@PathVariable String numero) {

    ProcessoDTO dto = service.getProcessoByNumero(numero);
    return ResponseEntity.ok(dto);
  }

  @DeleteMapping(value = "/{numero}")
  public ResponseEntity<Void> delete(@PathVariable String numero) {
    service.delete(numero);
    return ResponseEntity.noContent().build();
  }

}
