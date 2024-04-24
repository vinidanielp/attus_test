package com.attus.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record EnderecoDTO(
		
		Long id,
		
		@NotBlank(message = "Logradouro não pode estar em branco") 
		String logradouro,
		
		@NotBlank(message = "CEP não pode estar em branco") 
		String cep,
		
		@NotNull(message = "Numero não pode estar em branco") 
		String numero,
		
		@NotBlank(message = "Cidade não pode estar em branco") 
		String cidade,

		@NotBlank(message = "Estado não pode estar em branco") 
		String estado,
		
		Boolean isPrincipal
		) {

}
