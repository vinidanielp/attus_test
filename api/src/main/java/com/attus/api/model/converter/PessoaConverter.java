package com.attus.api.model.converter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.attus.api.dto.PessoaDTO;
import com.attus.api.model.Pessoa;

public class PessoaConverter {

	public static Pessoa toEntity(PessoaDTO dto) {
		return Pessoa.builder()
				.id(dto.id())
				.nomeCompleto(dto.nomeCompleto())
				.dataNascimento(dto.dataNascimento())
				.enderecos(dto.enderecos() != null ? EnderecoConverter.toEntityList(dto.enderecos()) : null)
				.build();
	}
	
    public static List<PessoaDTO> toListDTO(List<Pessoa> pessoas) {
        return pessoas.stream()
                .map(PessoaConverter::toDTO)
                .collect(Collectors.toList());
    }

	public static PessoaDTO toDTO(Pessoa pessoa) {
		return new PessoaDTO(
				pessoa.getId(), 
				pessoa.getNomeCompleto(), 
				pessoa.getDataNascimento(),
				pessoa.getEnderecos() != null ? EnderecoConverter.toListDTO(pessoa.getEnderecos()) : Collections.emptyList());
	}
	    
}
