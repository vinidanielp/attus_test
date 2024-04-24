package com.attus.api.service;

import org.springframework.stereotype.Service;

import com.attus.api.exception.ResourceNotFoundException;
import com.attus.api.model.Endereco;
import com.attus.api.model.Pessoa;
import com.attus.api.repository.EnderecoRepository;
import com.attus.api.repository.PessoaRepository;

@Service
public class EnderecoService {

	private final EnderecoRepository enderecoRepository;
	private final PessoaRepository pessoaRepository;

	public EnderecoService(EnderecoRepository enderecoRepository, PessoaRepository pessoaRepository) {
		this.enderecoRepository = enderecoRepository;
		this.pessoaRepository = pessoaRepository;
	}

	public Endereco criarEndereco(Long pessoaId, Endereco endereco) {
		Pessoa pessoa = pessoaRepository.findById(pessoaId)
				.orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada"));
		endereco.setPessoa(pessoa);
		return enderecoRepository.save(endereco);
	}

	public Endereco editarEndereco(Long id, Endereco enderecoAtualizado) {
		return enderecoRepository.findById(id).map(endereco -> {
			endereco.setLogradouro(enderecoAtualizado.getLogradouro());
			endereco.setCep(enderecoAtualizado.getCep());
			endereco.setNumero(enderecoAtualizado.getNumero());
			endereco.setCidade(enderecoAtualizado.getCidade());
			endereco.setEstado(enderecoAtualizado.getEstado());
			endereco.setPrincipal(enderecoAtualizado.isPrincipal());
			return enderecoRepository.save(endereco);
		}).orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado"));
	}

	public Endereco consultarEndereco(Long id) {
		return enderecoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado"));
	}

	public void definirEnderecoPrincipal(Long id) {
		Endereco endereco = enderecoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado"));
		endereco.setPrincipal(true);
		enderecoRepository.save(endereco);
	}
}
