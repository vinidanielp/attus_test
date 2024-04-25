package com.attus.api.converter.mock;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.attus.api.dto.PessoaDTO;
import com.attus.api.model.Pessoa;

public class PessoaMock {

    public Pessoa mockEntity() {
        return mockEntity(0);
    }

    public PessoaDTO mockDTO() {
        return mockDTO(0);
    }

    public List<Pessoa> mockEntityList() {
        return IntStream.range(0, 10).mapToObj(this::mockEntity).collect(Collectors.toList());
    }

    public List<PessoaDTO> mockDTOList() {
        return IntStream.range(0, 10).mapToObj(this::mockDTO).collect(Collectors.toList());
    }

    public Pessoa mockEntity(Integer numero) {
        return Pessoa.builder()
                .id(numero.longValue())
                .nomeCompleto("Vinicius D" + numero)
                .dataNascimento(LocalDate.of(2001, 3, 04))
                .enderecos(Collections.emptyList())
                .build();
    }

    public PessoaDTO mockDTO(Integer numero) {
        return new PessoaDTO(
        		numero.longValue(),
                "Vinicius D" + numero,
                LocalDate.of(2001, 3, 04),
                Collections.emptyList()
        );
    }
}
