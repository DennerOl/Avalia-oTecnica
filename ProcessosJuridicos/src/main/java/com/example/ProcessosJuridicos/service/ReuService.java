package com.example.ProcessosJuridicos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProcessosJuridicos.model.Reu;
import com.example.ProcessosJuridicos.repository.ReuRepository;

@Service
public class ReuService {
  @Autowired
  private ReuRepository reuRepository;

  public Reu saveReu(Reu reu) {
    return reuRepository.save(reu);
  }
}