package com.attus.api.mockito.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.attus.api.controller.EnderecoController;
import com.attus.api.dto.EnderecoDTO;
import com.attus.api.service.EnderecoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@WebMvcTest(EnderecoController.class)
public class EnderecoControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EnderecoService enderecoService;
	
	private ObjectMapper objectMapper;

	@BeforeEach
	public void setUp() {
		objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
	}

	@Test
	public void deveriaCriarEnderecoController() throws Exception {
		Long id = 1L;
		EnderecoDTO data = new EnderecoDTO(null, "Logradouro", "CEP", "Numero", "Cidade", "Estado", false);
		EnderecoDTO enderecoEsperado = new EnderecoDTO(1L, "Logradouro", "CEP", "Numero", "Cidade", "Estado", true);
		when(enderecoService.create(id, data)).thenReturn(enderecoEsperado);

		mockMvc.perform(post("/api/enderecos/{id}", id).contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(data)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").value(1L))
				.andExpect(jsonPath("$.logradouro").value("Logradouro"))
				.andExpect(jsonPath("$.cep").value("CEP"))
				.andExpect(jsonPath("$.numero").value("Numero"))
				.andExpect(jsonPath("$.cidade").value("Cidade"))
				.andExpect(jsonPath("$.estado").value("Estado"))
				.andExpect(jsonPath("$.isPrincipal").value(true));

		verify(enderecoService, times(1)).create(id, data);
	}

	@Test
	public void deveriaAtualizarEnderecoController() throws Exception {
		Long idEndereco = 1L;
		Long idPessoa = 1L;
		EnderecoDTO data = new EnderecoDTO(1L, "Logradouro", "CEP", "Numero", "Cidade", "Estado", false);

		when(enderecoService.update(idEndereco, idPessoa, data)).thenReturn(data);

		mockMvc.perform(put("/api/enderecos/{idEndereco}/{idPessoa}", idEndereco, idPessoa)
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(data)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1L))
				.andExpect(jsonPath("$.logradouro").value("Logradouro"))
				.andExpect(jsonPath("$.cep").value("CEP"))
				.andExpect(jsonPath("$.numero").value("Numero"))
				.andExpect(jsonPath("$.cidade").value("Cidade"))
				.andExpect(jsonPath("$.estado").value("Estado"))
				.andExpect(jsonPath("$.isPrincipal").value(false));

		verify(enderecoService, times(1)).update(idEndereco, idPessoa, data);
	}

	@Test
	public void deveriaColocarEnderecoComoPrincipalController() throws Exception {
		Long idEndereco = 1L;
		Long idPessoa = 1L;

		doNothing().when(enderecoService).setEnderecoPrincial(idPessoa, idEndereco);

		mockMvc.perform(patch("/api/enderecos/{idPessoa}/{idEndereco}", idPessoa, idEndereco))
				.andExpect(status().isOk())
				.andExpect(content().string("Novo endereço principal definido!"));

		verify(enderecoService, times(1)).setEnderecoPrincial(idPessoa, idEndereco);
	}

	@Test
	public void deveriaEncontrarPeloIdDoEnderecoController() throws Exception {
		Long id = 1L;
		EnderecoDTO enderecoEsperado = new EnderecoDTO(1L, "Logradouro", "CEP", "Numero", "Cidade", "Estado", false);

		when(enderecoService.findById(id)).thenReturn(enderecoEsperado);

		mockMvc.perform(get("/api/enderecos/{id}", id)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1L))
				.andExpect(jsonPath("$.logradouro").value("Logradouro"))
				.andExpect(jsonPath("$.cep").value("CEP"))
				.andExpect(jsonPath("$.numero").value("Numero"))
				.andExpect(jsonPath("$.cidade").value("Cidade"))
				.andExpect(jsonPath("$.estado").value("Estado"))
				.andExpect(jsonPath("$.isPrincipal").value(false));

		verify(enderecoService, times(1)).findById(id);
	}

	@Test
	public void deveriaEncontrarTodosOsEnderecosController() throws Exception {
		List<EnderecoDTO> enderecos = getEnderecosDTO();

		when(enderecoService.findAll()).thenReturn(enderecos);

		mockMvc.perform(get("/api/enderecos")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(1L))
				.andExpect(jsonPath("$[0].logradouro").value("Logradouro"))
				.andExpect(jsonPath("$[0].cep").value("CEP"))
				.andExpect(jsonPath("$[1].id").value(2L))
				.andExpect(jsonPath("$[1].logradouro").value("Logradouro2"))
				.andExpect(jsonPath("$[1].cep").value("CEP2"));

		verify(enderecoService, times(1)).findAll();
	}
	
	private static List<EnderecoDTO> getEnderecosDTO() {
        List<EnderecoDTO> enderecos = new ArrayList<>();

        EnderecoDTO data1 = new EnderecoDTO(1L, "Logradouro", "CEP", "Numero", "Cidade", "Estado", false);
        enderecos.add(data1);

        EnderecoDTO data2 = new EnderecoDTO(2L, "Logradouro2", "CEP2", "Numero2", "Cidade2", "Estado2", false);
        enderecos.add(data2);
        
        return enderecos;
    }

}
