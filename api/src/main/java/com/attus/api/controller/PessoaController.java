package com.attus.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attus.api.dto.PessoaDTO;
import com.attus.api.service.PessoaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pessoas")
public class PessoaController {
	
	private final PessoaService pessoaService;

	@PostMapping
	public ResponseEntity<PessoaDTO> create(@Valid @RequestBody PessoaDTO data) {
		PessoaDTO pessoaDTO = pessoaService.create(data);  
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PessoaDTO> update(@PathVariable Long id, @Valid @RequestBody PessoaDTO data) {
		PessoaDTO pessoaDTO = pessoaService.update(id, data);
		return ResponseEntity.status(HttpStatus.OK).body(pessoaDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PessoaDTO> findById(@PathVariable Long id) {
		PessoaDTO pessoaDTO = pessoaService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(pessoaDTO);
	}
	
    @GetMapping
    public ResponseEntity<List<PessoaDTO>> findAll() {
    	List<PessoaDTO> pessoas = pessoaService.findAll();
    	return ResponseEntity.status(HttpStatus.OK).body(pessoas);
    }
}
