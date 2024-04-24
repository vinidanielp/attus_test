package com.attus.api.model.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.attus.api.dto.EnderecoDTO;
import com.attus.api.model.Endereco;

public class EnderecoConverter {
	
    public static EnderecoDTO toDTO(Endereco endereco) {
        return new EnderecoDTO(
        		endereco.getId(),
        		endereco.getLogradouro(),
        		endereco.getCep(),
        		endereco.getNumero(),
        		endereco.getCidade(),
        		endereco.getEstado(),
        		endereco.getIsPrincipal()
        );
    }

    public static Endereco toEntity(EnderecoDTO dto) {
        return Endereco.builder()
                .id(dto.id())
                .logradouro(dto.logradouro())
                .cep(dto.cep())
                .numero(dto.numero())
                .cidade(dto.cidade())
                .estado(dto.estado())
                .isPrincipal(dto.isPrincipal())
                .build();
    }

    public static List<EnderecoDTO> toListDTO(List<Endereco> enderecos) {
        return enderecos.stream()
                .map(EnderecoConverter::toDTO)
                .collect(Collectors.toList());
    }

    public static List<Endereco> toEntityList(List<EnderecoDTO> enderecosDTO) {
        return enderecosDTO.stream()
                .map(EnderecoConverter::toEntity)
                .collect(Collectors.toList());
    }
}
