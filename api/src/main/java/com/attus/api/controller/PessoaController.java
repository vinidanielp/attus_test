package com.attus.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attus.api.model.Pessoa;
import com.attus.api.service.PessoaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {
	
	private final PessoaService pessoaService;

	public PessoaController(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}

	@PostMapping
	public ResponseEntity<Pessoa> criarPessoa(@Valid @RequestBody Pessoa pessoa) {
		Pessoa pessoaCriada = pessoaService.criarPessoa(pessoa);
		return new ResponseEntity<>(pessoaCriada, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> editarPessoa(@PathVariable Long id, @Valid @RequestBody Pessoa pessoaAtualizada) {
		Pessoa pessoaEditada = pessoaService.editarPessoa(id, pessoaAtualizada);
		return new ResponseEntity<>(pessoaEditada, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> consultarPessoa(@PathVariable Long id) {
		Pessoa pessoa = pessoaService.consultarPessoa(id);
		return new ResponseEntity<>(pessoa, HttpStatus.OK);
	}
}
