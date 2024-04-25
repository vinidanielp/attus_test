package com.attus.api.mockito.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.attus.api.controller.PessoaController;
import com.attus.api.dto.PessoaDTO;
import com.attus.api.service.PessoaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@WebMvcTest(PessoaController.class)
public class PessoaControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PessoaService pessoaService;
	
	private ObjectMapper objectMapper;

	@BeforeEach
	public void setUp() {
		objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
	}

	@Test
	public void deveriaCriarPessoaController() throws Exception {
		var pessoa = new PessoaDTO(null, "Vinicius Dan", LocalDate.of(2000, 1, 15), Collections.emptyList());
		var novaPessoa = new PessoaDTO(1L, "Vinicius Dan", LocalDate.of(2000, 1, 15), Collections.emptyList());

		when(pessoaService.create(pessoa)).thenReturn(novaPessoa);

		mockMvc.perform(post("/api/pessoas").contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(pessoa)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").value(1L))
				.andExpect(jsonPath("$.nome_completo").value("Vinicius Dan"))
				.andExpect(jsonPath("$.data_nascimento").value("2000-01-15"));

		verify(pessoaService, times(1)).create(pessoa);
	}

	@Test
	public void deveriaAtualizarPessoaController() throws Exception {
		var pessoa = new PessoaDTO(1L, "Vinicius Dan", LocalDate.of(2000, 1, 15), Collections.emptyList());

		var updatedPessoa = new PessoaDTO(1L, "Vinicius Daniel", LocalDate.of(2000, 1, 15), Collections.emptyList());

		when(pessoaService.update(1L, pessoa)).thenReturn(updatedPessoa);

		mockMvc.perform(put("/api/pessoas/{id}", pessoa.id()).contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(pessoa)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1L))
				.andExpect(jsonPath("$.nome_completo").value("Vinicius Daniel"))
				.andExpect(jsonPath("$.data_nascimento").value("2000-01-15"));

		verify(pessoaService, times(1)).update(1L, pessoa);
	}

	@Test
	public void deveriaEncontrarPorIdDaPessoaController() throws Exception {
		var pessoa = new PessoaDTO(1L, "Vinicius Daniel", LocalDate.of(2000, 1, 15), Collections.emptyList());

		when(pessoaService.findById(1L)).thenReturn(pessoa);

		mockMvc.perform(get("/api/pessoas/{id}", pessoa.id())
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(pessoa)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1L))
				.andExpect(jsonPath("$.nome_completo").value("Vinicius Daniel"))
				.andExpect(jsonPath("$.data_nascimento").value("2000-01-15"));

		verify(pessoaService, times(1)).findById(1L);
	}

	@Test
	public void deveriaEncontrarTodasAsPessoasController() throws Exception {
		List<PessoaDTO> pessoas = new ArrayList<>();
		var pessoa1 = new PessoaDTO(1L, "Vinicius Daniel", LocalDate.of(2000, 1, 15), Collections.emptyList());
		pessoas.add(pessoa1);
		
		var pessoa2 = new PessoaDTO(2L, "Mauro Costa", LocalDate.of(1993, 9, 25), Collections.emptyList());
		pessoas.add(pessoa2);

		when(pessoaService.findAll()).thenReturn(pessoas);

		mockMvc.perform(get("/api/pessoas"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(1))
				.andExpect(jsonPath("$[0].nome_completo").value("Vinicius Daniel"))
				.andExpect(jsonPath("$[1].id").value(2))
				.andExpect(jsonPath("$[1].nome_completo").value("Mauro Costa"));

		verify(pessoaService, times(1)).findAll();
	}

}
