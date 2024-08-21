package com.example.ProcessosJuridicos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ProcessosJuridicos.model.Processo;

public interface ProcessoRepository extends JpaRepository<Processo, Long> {

  boolean existsByNumero(String numero);

  Optional<Processo> findByProcessNumber(String processNumber);

  void deleteByNumero(String numero);

}
