package com.attus.api.service;

import java.util.List;

import com.attus.api.dto.EnderecoDTO;

public interface EnderecoService {
	
	EnderecoDTO create(Long id, EnderecoDTO data);

	EnderecoDTO update(Long idEndereco, Long idPessoa, EnderecoDTO data);

	void setEnderecoPrincial(Long idPessoa, Long idEndereco);

	EnderecoDTO findById(Long id);

	List<EnderecoDTO> findAll();
}
