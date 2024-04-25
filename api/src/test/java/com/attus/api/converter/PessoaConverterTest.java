package com.attus.api.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.attus.api.converter.mock.PessoaMock;
import com.attus.api.model.converter.PessoaConverter;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class PessoaConverterTest {

	PessoaMock inputObject;

    @BeforeEach
    public void setUp() {
        inputObject = new PessoaMock();
    }

    @Test
    @Order(1)
    public void deveAnalisarAEntidadeToDTO() {
        var pessoaEntity = inputObject.mockEntity(1);
        var pessoaDTO = PessoaConverter.toDTO(pessoaEntity);

        assertThat(pessoaDTO.id()).isEqualTo(1L);
        assertThat(pessoaDTO.nomeCompleto()).isEqualTo("Vinicius D1");
        assertThat(pessoaDTO.dataNascimento()).isEqualTo(LocalDate.of(2001, 3, 04));
    }

    @Test
    @Order(2)
    public void deveAnalisarAListaDeEntidadesToListDTO(){
        var outPutListEntity = inputObject.mockEntityList();
        var outPutListDTO = PessoaConverter.toListDTO(outPutListEntity);

        var outPutOne = outPutListDTO.get(1);

        assertThat(outPutOne.id()).isEqualTo(1L);
        assertThat(outPutOne.nomeCompleto()).isEqualTo("Vinicius D1");
        assertThat(outPutOne.dataNascimento()).isEqualTo(LocalDate.of(2001, 3, 04));

        var outPutFive = outPutListDTO.get(5);

        assertThat(outPutFive.id()).isEqualTo(5L);
        assertThat(outPutFive.nomeCompleto()).isEqualTo("Vinicius D5");
        assertThat(outPutFive.dataNascimento()).isEqualTo(LocalDate.of(2001, 3, 04));

        var outPutSeven = outPutListDTO.get(7);

        assertThat(outPutSeven.id()).isEqualTo(7L);
        assertThat(outPutSeven.nomeCompleto()).isEqualTo("Vinicius D7");
        assertThat(outPutSeven.dataNascimento()).isEqualTo(LocalDate.of(2001, 3, 04));
    }

    @Test
    @Order(3)
    public void deveAnalisarDTOToEntity(){
        var pessoaDTO = inputObject.mockDTO(1);
        var pessoaEntity = PessoaConverter.toEntity(pessoaDTO);

        assertThat(pessoaEntity.getId()).isEqualTo(1L);
        assertThat(pessoaEntity.getNomeCompleto()).isEqualTo("Vinicius D1");
        assertThat(pessoaEntity.getDataNascimento()).isEqualTo(LocalDate.of(2001, 3, 04));
        assertThat(pessoaEntity.getEnderecos()).isEmpty();
    }
}
