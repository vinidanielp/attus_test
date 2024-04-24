package com.attus.api.service;

import org.springframework.stereotype.Service;

import com.attus.api.exception.ResourceNotFoundException;
import com.attus.api.model.Pessoa;
import com.attus.api.repository.PessoaRepository;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa criarPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Pessoa editarPessoa(Long id, Pessoa pessoaAtualizada) {
        return pessoaRepository.findById(id)
            .map(pessoa -> {
                pessoa.setNomeCompleto(pessoaAtualizada.getNomeCompleto());
                pessoa.setDataNascimento(pessoaAtualizada.getDataNascimento());
                return pessoaRepository.save(pessoa);
            })
            .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada"));
    }

    public Pessoa consultarPessoa(Long id) {
        return pessoaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada"));
    }
}
