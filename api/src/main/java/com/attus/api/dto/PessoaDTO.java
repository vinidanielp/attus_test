package com.attus.api.dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record PessoaDTO(
		
		Long id,
		
        @NotBlank(message = "Nome Completo não pode estar em branco")
        @JsonProperty("nome_completo")
        String nomeCompleto,
        
        @NotNull(message = "Data de Nascimento não pode estar em branco")
	    @DateTimeFormat(pattern = "dd/MM/yyyy")
        @JsonProperty("data_nascimento")
        LocalDate dataNascimento,
        
        @JsonProperty("lista_enderecos")
        List<EnderecoDTO> enderecos
) {

}
