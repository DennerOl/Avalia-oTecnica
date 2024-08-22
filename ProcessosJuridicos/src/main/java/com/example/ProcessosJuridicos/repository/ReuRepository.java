package com.example.ProcessosJuridicos.repository;

import com.example.ProcessosJuridicos.model.Reu;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReuRepository extends JpaRepository<Reu, Long> {


  Optional<Reu> findByCpf(String cpf);
}
