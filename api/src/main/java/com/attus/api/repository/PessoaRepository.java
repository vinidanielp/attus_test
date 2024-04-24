package com.attus.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.attus.api.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
}
