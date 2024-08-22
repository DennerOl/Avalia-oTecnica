package com.example.ProcessosJuridicos.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Processo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Size(min = 1, max = 255)
  @Column(unique = true)
  private String numero;

 
@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "processo_reu",
	joinColumns = @JoinColumn(name = "processo_id"),
	inverseJoinColumns = @JoinColumn(name = "reu_id"))
	private Set<Reu> reus = new HashSet<>();
	

}
