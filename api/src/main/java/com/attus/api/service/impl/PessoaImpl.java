package com.attus.api.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.attus.api.dto.PessoaDTO;
import com.attus.api.exception.ResourceNotFoundException;
import com.attus.api.model.Pessoa;
import com.attus.api.model.converter.PessoaConverter;
import com.attus.api.repository.PessoaRepository;
import com.attus.api.service.PessoaService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PessoaImpl implements PessoaService{
	
    private final PessoaRepository pessoaRepository;

	@Override
	public PessoaDTO create(PessoaDTO data) {
		
		Pessoa pessoa = PessoaConverter.toEntity(data);
		Pessoa novaPessoa = pessoaRepository.save(pessoa);
		
		return PessoaConverter.toDTO(novaPessoa);
	}

	@Override
	public PessoaDTO update(Long id, PessoaDTO data) {
		
		Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada"));
	                
		pessoa.setNomeCompleto(data.nomeCompleto());
		pessoa.setDataNascimento(data.dataNascimento());

		Pessoa updatePessoa = pessoaRepository.save(pessoa);   

		return PessoaConverter.toDTO(updatePessoa);
	}

	@Override
	public PessoaDTO findById(Long id) {
		
		Pessoa pessoa = pessoaRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada"));
		
		return PessoaConverter.toDTO(pessoa);
	}

	@Override
	public List<PessoaDTO> findAll() {
		List<Pessoa> pessoas = pessoaRepository.findAll();
		return PessoaConverter.toListDTO(pessoas);
	}
}
