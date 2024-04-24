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

import com.attus.api.model.Endereco;
import com.attus.api.service.EnderecoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {
	private final EnderecoService enderecoService;

	public EnderecoController(EnderecoService enderecoService) {
		this.enderecoService = enderecoService;
	}

	@PostMapping("/{pessoaId}")
	public ResponseEntity<Endereco> criarEndereco(@PathVariable Long pessoaId, @Valid @RequestBody Endereco endereco) {
		Endereco enderecoCriado = enderecoService.criarEndereco(pessoaId, endereco);
		return new ResponseEntity<>(enderecoCriado, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Endereco> editarEndereco(@PathVariable Long id,
			@Valid @RequestBody Endereco enderecoAtualizado) {
		Endereco enderecoEditado = enderecoService.editarEndereco(id, enderecoAtualizado);
		return new ResponseEntity<>(enderecoEditado, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Endereco> consultarEndereco(@PathVariable Long id) {
		Endereco endereco = enderecoService.consultarEndereco(id);
		return new ResponseEntity<>(endereco, HttpStatus.OK);
	}

	@PutMapping("/principal/{id}")
	public ResponseEntity<Void> definirEnderecoPrincipal(@PathVariable Long id) {
		enderecoService.definirEnderecoPrincipal(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
