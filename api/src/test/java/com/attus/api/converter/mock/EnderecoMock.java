package com.attus.api.converter.mock;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.attus.api.dto.EnderecoDTO;
import com.attus.api.model.Endereco;

public class EnderecoMock {

	private final PessoaMock pessoaMock = new PessoaMock();

	public Endereco mockEntity() {
		return mockEntity(0);
	}

	public EnderecoDTO mockDTO() {
		return mockDTO(0);
	}

	public List<Endereco> mockEntityList() {
		return IntStream.range(0, 10).mapToObj(this::mockEntity).collect(Collectors.toList());
	}

	public List<EnderecoDTO> mockDTOList() {
		return IntStream.range(0, 10).mapToObj(this::mockDTO).collect(Collectors.toList());
	}

	public Endereco mockEntity(Integer number) {
		var pessoa = pessoaMock.mockEntity(number);
		return Endereco.builder()
				.id(number.longValue())
				.logradouro("Logradouro" + number)
				.cep("CEP" + number)
				.numero("Numero" + number)
				.cidade("Cidade" + number)
				.estado("Estado" + number)
				.isPrincipal(false)
				.pessoa(pessoa)
				.build();
	}

	public EnderecoDTO mockDTO(Integer number) {
		return new EnderecoDTO(number.longValue(), 
				"Logradouro" + number, 
				"CEP" + number, 
				"Numero" + number, 
				"Cidade" + number,
				"Estado" + number, 
				false
		);

	}
}
