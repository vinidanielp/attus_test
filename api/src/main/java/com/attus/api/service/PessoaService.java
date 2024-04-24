package com.attus.api.service;

import java.util.List;

import com.attus.api.dto.PessoaDTO;

public interface PessoaService {

	PessoaDTO create(PessoaDTO data);

	PessoaDTO update(Long id, PessoaDTO data);

	PessoaDTO findById(Long id);

	List<PessoaDTO> findAll();

}
