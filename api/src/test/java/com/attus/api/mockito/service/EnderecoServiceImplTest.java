package com.attus.api.mockito.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.attus.api.converter.mock.EnderecoMock;
import com.attus.api.converter.mock.PessoaMock;
import com.attus.api.model.Endereco;
import com.attus.api.model.converter.EnderecoConverter;
import com.attus.api.repository.EnderecoRepository;
import com.attus.api.repository.PessoaRepository;
import com.attus.api.service.impl.EnderecoServiceImpl;

public class EnderecoServiceImplTest {

	EnderecoMock input;
	PessoaMock inputPessoa;

	@Mock
	private EnderecoRepository enderecoRepository;

	@Mock
	private PessoaRepository pessoaRepository;

	@InjectMocks
	private EnderecoServiceImpl enderecoServiceImpl;

	@BeforeEach
	public void setup() {
		input = new EnderecoMock();
		inputPessoa = new PessoaMock();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void deveriaCriarEndereco() {
		var enderecoDTO = input.mockDTO(1);

		var pessoa = inputPessoa.mockEntity(1);

		var endereco = EnderecoConverter.toEntity(enderecoDTO);
		endereco.setPessoa(pessoa);
		endereco.setIsPrincipal(true);

		var novoEndereco = input.mockEntity(1);

		when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));
		when(enderecoRepository.save(any(Endereco.class))).thenReturn(novoEndereco);

		var result = enderecoServiceImpl.create(1L, enderecoDTO);

		verify(pessoaRepository, times(1)).findById(1L);
		verify(enderecoRepository, times(1)).save(any(Endereco.class));
		assertThat(result.id()).isEqualTo(novoEndereco.getId());
		assertThat(result.logradouro()).isEqualTo(novoEndereco.getLogradouro());
		assertThat(result.cep()).isEqualTo(novoEndereco.getCep());
		assertThat(result.numero()).isEqualTo(novoEndereco.getNumero());
		assertThat(result.cidade()).isEqualTo(novoEndereco.getCidade());
		assertThat(result.estado()).isEqualTo(novoEndereco.getEstado());
	}

	@Test
	public void deveriaAtualizarEndereco() {
		var enderecoDTO = input.mockDTO(1);

		var endereco = input.mockEntity(1);

		var updatedEndereco = input.mockEntity(1);
		updatedEndereco.setLogradouro("Rua Maranhão");

		when(enderecoRepository.findById(1L)).thenReturn(Optional.of(endereco));
		when(enderecoRepository.save(any(Endereco.class))).thenReturn(updatedEndereco);

		var result = enderecoServiceImpl.update(1L, 1L, enderecoDTO);

		verify(enderecoRepository, times(1)).findById(1L);
		verify(enderecoRepository, times(1)).save(any(Endereco.class));
		assertThat(updatedEndereco.getId()).isEqualTo(result.id());
		assertThat(updatedEndereco.getLogradouro()).isEqualTo(result.logradouro());
		assertThat(updatedEndereco.getCep()).isEqualTo(result.cep());
		assertThat(updatedEndereco.getNumero()).isEqualTo(result.numero());
		assertThat(updatedEndereco.getCidade()).isEqualTo(result.cidade());
		assertThat(updatedEndereco.getEstado()).isEqualTo(result.estado());
	}

	@Test
	public void deveriaColocarComoEnderecoPrincipal() {
		var pessoa = inputPessoa.mockEntity(1);

		var endereco = input.mockEntity(1);
		endereco.setPessoa(pessoa);

		when(enderecoRepository.findById(1L)).thenReturn(Optional.of(endereco));

		enderecoServiceImpl.setEnderecoPrincial(1L, 1L);

		verify(enderecoRepository, times(1)).findById(1L);
		verify(enderecoRepository, times(1)).updateEnderecoPrincipal(1L, 1L);
	}

	@Test
	public void deveriaEncontrarEnderecoPorId() {
		var endereco = input.mockEntity(1);

		when(enderecoRepository.findById(1L)).thenReturn(Optional.of(endereco));

		var result = enderecoServiceImpl.findById(1L);

		verify(enderecoRepository, times(1)).findById(1L);

		assertThat(endereco.getId()).isEqualTo(result.id());
		assertThat(endereco.getLogradouro()).isEqualTo(result.logradouro());
		assertThat(endereco.getCep()).isEqualTo(result.cep());
		assertThat(endereco.getNumero()).isEqualTo(result.numero());
		assertThat(endereco.getCidade()).isEqualTo(result.cidade());
		assertThat(endereco.getEstado()).isEqualTo(result.estado());
	}

	@Test
	public void deveriaEncontrarTodosOsEnderecos() {
		var enderecos = input.mockEntityList();

		when(enderecoRepository.findAll()).thenReturn(enderecos);

		var result = enderecoServiceImpl.findAll();

		verify(enderecoRepository, times(1)).findAll();
		assertThat(enderecos.size()).isEqualTo(result.size());
		for (int i = 0; i < enderecos.size(); i++) {
			var address = enderecos.get(i);
			var addressDTO = result.get(i);

			assertThat(address.getId()).isEqualTo(addressDTO.id());
			assertThat(address.getLogradouro()).isEqualTo(addressDTO.logradouro());
			assertThat(address.getCep()).isEqualTo(addressDTO.cep());
			assertThat(address.getNumero()).isEqualTo(addressDTO.numero());
			assertThat(address.getCidade()).isEqualTo(addressDTO.cidade());
			assertThat(address.getEstado()).isEqualTo(addressDTO.estado());
		}
	}
}
