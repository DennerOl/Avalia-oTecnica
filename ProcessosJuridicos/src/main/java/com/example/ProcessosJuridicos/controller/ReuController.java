package com.example.ProcessosJuridicos.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProcessosJuridicos.dto.ReuDTO;
import com.example.ProcessosJuridicos.service.ReuService;

@RestController
@RequestMapping("/reus")
public class ReuController {

  @Autowired
  private ReuService reuService;

  @PostMapping("/{processoNumber}/reus")
  public ResponseEntity<Set<ReuDTO>> addReusToProcesso(@PathVariable String processoNumber,
      @RequestBody Set<ReuDTO> reusDTO) {
    Set<ReuDTO> savedReus = reuService.addReusToProcesso(processoNumber, reusDTO);
    return ResponseEntity.ok(savedReus);
  }


  @GetMapping
	public ResponseEntity<Set<ReuDTO>> findAll() {
		Set<ReuDTO> dto = reuService.findAll();
		return ResponseEntity.ok(dto);
	}



}
