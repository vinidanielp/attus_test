package com.attus.api.converter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.attus.api.converter.mock.EnderecoMock;
import com.attus.api.model.converter.EnderecoConverter;

public class EnderecoConverterTest {

	EnderecoMock inputObject;

	@BeforeEach
	public void setUp() {
		inputObject = new EnderecoMock();
	}

	@Test
	@Order(1)
	public void deveAnalisarAEntidadeToDTO() {
		var enderecoEntity = inputObject.mockEntity(1);
		var enderecoDTO = EnderecoConverter.toDTO(enderecoEntity);

		assertThat(enderecoDTO.id()).isEqualTo(1L);
		assertThat(enderecoDTO.logradouro()).isEqualTo("Logradouro1");
		assertThat(enderecoDTO.cep()).isEqualTo("CEP1");
		assertThat(enderecoDTO.numero()).isEqualTo("Numero1");
		assertThat(enderecoDTO.cidade()).isEqualTo("Cidade1");
		assertThat(enderecoDTO.estado()).isEqualTo("Estado1");
		assertThat(enderecoDTO.isPrincipal()).isEqualTo(false);
	}

	@Test
	@Order(2)
	public void deveAnalisarAListaDeEntidadesToListDTO() {
		var outPutListEntity = inputObject.mockEntityList();
		var outPutListDTO = EnderecoConverter.toListDTO(outPutListEntity);

		var outPutOne = outPutListDTO.get(1);

		assertThat(outPutOne.id()).isEqualTo(1L);
		assertThat(outPutOne.logradouro()).isEqualTo("Logradouro1");
		assertThat(outPutOne.cep()).isEqualTo("CEP1");
		assertThat(outPutOne.numero()).isEqualTo("Numero1");
		assertThat(outPutOne.cidade()).isEqualTo("Cidade1");
		assertThat(outPutOne.estado()).isEqualTo("Estado1");
		assertThat(outPutOne.isPrincipal()).isEqualTo(false);

		var outPutFive = outPutListDTO.get(5);
		
		assertThat(outPutFive.id()).isEqualTo(5L);
		assertThat(outPutFive.logradouro()).isEqualTo("Logradouro5");
		assertThat(outPutFive.cep()).isEqualTo("CEP5");
		assertThat(outPutFive.numero()).isEqualTo("Numero5");
		assertThat(outPutFive.cidade()).isEqualTo("Cidade5");
		assertThat(outPutFive.estado()).isEqualTo("Estado5");
		assertThat(outPutFive.isPrincipal()).isEqualTo(false);

		var outPutSeven = outPutListDTO.get(7);
		
		assertThat(outPutSeven.id()).isEqualTo(7L);
		assertThat(outPutSeven.logradouro()).isEqualTo("Logradouro7");
		assertThat(outPutSeven.cep()).isEqualTo("CEP7");
		assertThat(outPutSeven.numero()).isEqualTo("Numero7");
		assertThat(outPutSeven.cidade()).isEqualTo("Cidade7");
		assertThat(outPutSeven.estado()).isEqualTo("Estado7");
		assertThat(outPutSeven.isPrincipal()).isEqualTo(false);

	}

	@Test
	@Order(3)
	public void deveAnalisarDTOToEntity() {
		var enderecoDTO = inputObject.mockDTO(1);
		var enderecoEntity = EnderecoConverter.toEntity(enderecoDTO);

		assertThat(enderecoEntity.getId()).isEqualTo(1L);
		assertThat(enderecoEntity.getLogradouro()).isEqualTo("Logradouro1");
		assertThat(enderecoEntity.getCep()).isEqualTo("CEP1");
		assertThat(enderecoEntity.getNumero()).isEqualTo("Numero1");
		assertThat(enderecoEntity.getCidade()).isEqualTo("Cidade1");
		assertThat(enderecoEntity.getEstado()).isEqualTo("Estado1");
		assertThat(enderecoEntity.getIsPrincipal()).isEqualTo(false);
	}

	@Test
	@Order(4)
	public void deveAnalisarListDTOToEntityList() {
		var outPutListDTO = inputObject.mockDTOList();
		var outPutListEntity = EnderecoConverter.toEntityList(outPutListDTO);

		var outPutOne = outPutListEntity.get(1);
		
		assertThat(outPutOne.getId()).isEqualTo(1L);
		assertThat(outPutOne.getLogradouro()).isEqualTo("Logradouro1");
		assertThat(outPutOne.getCep()).isEqualTo("CEP1");
		assertThat(outPutOne.getNumero()).isEqualTo("Numero1");
		assertThat(outPutOne.getCidade()).isEqualTo("Cidade1");
		assertThat(outPutOne.getEstado()).isEqualTo("Estado1");
		assertThat(outPutOne.getIsPrincipal()).isEqualTo(false);

		var outPutFive = outPutListEntity.get(5);
		
		assertThat(outPutFive.getId()).isEqualTo(5L);
		assertThat(outPutFive.getLogradouro()).isEqualTo("Logradouro5");
		assertThat(outPutFive.getCep()).isEqualTo("CEP5");
		assertThat(outPutFive.getNumero()).isEqualTo("Numero5");
		assertThat(outPutFive.getCidade()).isEqualTo("Cidade5");
		assertThat(outPutFive.getEstado()).isEqualTo("Estado5");
		assertThat(outPutFive.getIsPrincipal()).isEqualTo(false);

		var outPutSeven = outPutListEntity.get(7);
		
		assertThat(outPutSeven.getId()).isEqualTo(7L);
		assertThat(outPutSeven.getLogradouro()).isEqualTo("Logradouro7");
		assertThat(outPutSeven.getCep()).isEqualTo("CEP7");
		assertThat(outPutSeven.getNumero()).isEqualTo("Numero7");
		assertThat(outPutSeven.getCidade()).isEqualTo("Cidade7");
		assertThat(outPutSeven.getEstado()).isEqualTo("Estado7");
		assertThat(outPutSeven.getIsPrincipal()).isEqualTo(false);
	}

}
