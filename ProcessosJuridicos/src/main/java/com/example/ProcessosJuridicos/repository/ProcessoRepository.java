package com.example.ProcessosJuridicos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ProcessosJuridicos.model.Processo;

import jakarta.transaction.Transactional;

@Repository
public interface ProcessoRepository extends JpaRepository<Processo, Long> {
  boolean existsByNumero(String numero);

  // @Query("SELECT p FROM Processo p LEFT JOIN FETCH p.reus WHERE p.numero =
  // :numero")

  @Query("SELECT p FROM Processo p WHERE p.numero = :numero")
  Optional<Processo> findByNumero(String numero);

  @Modifying
  @Transactional
  @Query("DELETE FROM Processo p WHERE p.numero = :numero")
  void deleteByNumero(String numero);

}