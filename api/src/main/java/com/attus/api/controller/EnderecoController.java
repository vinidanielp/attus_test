package com.attus.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attus.api.dto.EnderecoDTO;
import com.attus.api.service.EnderecoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/enderecos")
@RequiredArgsConstructor
public class EnderecoController {
	
	private final EnderecoService enderecoService;

	@PostMapping("/{id}")
	public ResponseEntity<EnderecoDTO> create(@PathVariable(value = "id") Long id, @Valid @RequestBody EnderecoDTO data) {
		EnderecoDTO enderecoDTO = enderecoService.create(id, data);
		return ResponseEntity.status(HttpStatus.CREATED).body(enderecoDTO);
	}

	@PutMapping("/{idEndereco}/{idPessoa}")
	public ResponseEntity<EnderecoDTO> update(@PathVariable Long idEndereco, @PathVariable(value = "idPessoa") Long idPessoa, @Valid @RequestBody EnderecoDTO data) {
		EnderecoDTO enderecoDTO = enderecoService.update(idEndereco, idPessoa, data);
		return ResponseEntity.status(HttpStatus.OK).body(enderecoDTO);
	}
	
	@PatchMapping("/{idPessoa}/{idEndereco}")	
    public ResponseEntity<?> setEnderecoPrincipal(@PathVariable(value = "idPessoa") Long idPessoa, @PathVariable(value = "idEndereco") Long idEndereco) {
		enderecoService.setEnderecoPrincial(idPessoa, idEndereco);
        return ResponseEntity.ok("Novo endereço principal definido!");
    }

	@GetMapping("/{id}")
	public ResponseEntity<EnderecoDTO> findById(@PathVariable(value = "id") Long id) {
		EnderecoDTO enderecoDTO = enderecoService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(enderecoDTO);
	}

	@GetMapping
    public ResponseEntity<List<EnderecoDTO>> findAll() {
		List<EnderecoDTO> enderecos = enderecoService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(enderecos);
	}
}
