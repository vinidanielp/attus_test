package com.attus.api.mockito.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.attus.api.converter.mock.PessoaMock;
import com.attus.api.dto.PessoaDTO;
import com.attus.api.exception.ResourceNotFoundException;
import com.attus.api.model.Pessoa;
import com.attus.api.model.converter.PessoaConverter;
import com.attus.api.repository.PessoaRepository;
import com.attus.api.service.impl.PessoaServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class PessoaServiceImplTest {
	
	PessoaMock input;
	
	@Mock
	private PessoaRepository pessoaRepository;

	@InjectMocks
	private PessoaServiceImpl pessoaServiceImpl;

	@BeforeEach
	public void setup() {
		input = new PessoaMock();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void deveriaCriarPessoa() {
		var pessoaDTO = input.mockDTO(1);

		var pessoa = PessoaConverter.toEntity(pessoaDTO);
		var pessoaNova = input.mockEntity(1);

		when(pessoaRepository.save(pessoa)).thenReturn(pessoaNova);

		var result = pessoaServiceImpl.create(pessoaDTO);

		verify(pessoaRepository, times(1)).save(pessoa);
		assertThat(pessoaNova.getId()).isEqualTo(result.id());
		assertThat(pessoaNova.getNomeCompleto()).isEqualTo(result.nomeCompleto());
		assertThat(pessoaNova.getDataNascimento()).isEqualTo(result.dataNascimento());
	}

	@Test
	public void deveriaAtualizarPessoa() {
		var pessoaDTO = input.mockDTO(1);

		var pessoa = input.mockEntity(1);

		var updatedPessoa = input.mockEntity(1);
		updatedPessoa.setNomeCompleto("David G");

		when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));
		when(pessoaRepository.save(pessoa)).thenReturn(updatedPessoa);

		var result = pessoaServiceImpl.update(1L, pessoaDTO);

		verify(pessoaRepository, times(1)).findById(1L);
		verify(pessoaRepository, times(1)).save(pessoa);
		assertThat(updatedPessoa.getId()).isEqualTo(result.id());
		assertThat(updatedPessoa.getNomeCompleto()).isEqualTo(result.nomeCompleto());
		assertThat(updatedPessoa.getDataNascimento()).isEqualTo(result.dataNascimento());
	}

	@Test
	public void quandoAAtualizacaoLancaResourceNotFoundException() {
		Long id = 1L;
		var pessoaDTO = new PessoaDTO(null, "Lucas da Silva", LocalDate.of(2001, 4, 16), Collections.emptyList());

		when(pessoaRepository.findById(id)).thenReturn(Optional.empty());

		assertThatThrownBy(() -> pessoaServiceImpl.update(id, pessoaDTO)).isInstanceOf(ResourceNotFoundException.class);
		verify(pessoaRepository, times(1)).findById(id);
		verify(pessoaRepository, never()).save(any());
	}

	@Test
	public void deveEncontrarPorIdPessoa() {
		var person = input.mockEntity(1);

		when(pessoaRepository.findById(1L)).thenReturn(Optional.of(person));

		var result = pessoaServiceImpl.findById(1L);

		verify(pessoaRepository, times(1)).findById(1L);
		assertThat(person.getId()).isEqualTo(result.id());
		assertThat(person.getNomeCompleto()).isEqualTo(result.nomeCompleto());
		assertThat(person.getDataNascimento()).isEqualTo(result.dataNascimento());
	}

	@Test
	public void quandoBsucarPorIdDaPessoaLancaResourceNotFoundException() {
		Long id = 1L;

		when(pessoaRepository.findById(id)).thenReturn(Optional.empty());

		assertThatThrownBy(() -> pessoaServiceImpl.findById(id)).isInstanceOf(ResourceNotFoundException.class);
		verify(pessoaRepository, times(1)).findById(id);
	}

	@Test
	public void deveEncontrarTodasAsPessoas() {
		List<Pessoa> personList = input.mockEntityList();

		when(pessoaRepository.findAll()).thenReturn(personList);

		List<PessoaDTO> result = pessoaServiceImpl.findAll();

		verify(pessoaRepository, times(1)).findAll();
		assertThat(personList.size()).isEqualTo(result.size());
		assertThat(personList.get(0).getId()).isEqualTo(result.get(0).id());
		assertThat(personList.get(0).getNomeCompleto()).isEqualTo(result.get(0).nomeCompleto());
		assertThat(personList.get(0).getDataNascimento()).isEqualTo(result.get(0).dataNascimento());
		assertThat(personList.get(1).getId()).isEqualTo(result.get(1).id());
		assertThat(personList.get(1).getNomeCompleto()).isEqualTo(result.get(1).nomeCompleto());
		assertThat(personList.get(1).getDataNascimento()).isEqualTo(result.get(1).dataNascimento());
	}
}
