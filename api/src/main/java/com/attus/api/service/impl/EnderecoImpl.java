package com.attus.api.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.attus.api.dto.EnderecoDTO;
import com.attus.api.exception.ResourceNotFoundException;
import com.attus.api.model.Endereco;
import com.attus.api.model.Pessoa;
import com.attus.api.model.converter.EnderecoConverter;
import com.attus.api.repository.EnderecoRepository;
import com.attus.api.repository.PessoaRepository;
import com.attus.api.service.EnderecoService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EnderecoImpl implements EnderecoService{
	
	private final EnderecoRepository enderecoRepository;
	
	private final PessoaRepository pessoaRepository;
	
	@Override
	public EnderecoDTO create(Long id, EnderecoDTO data) {

        Pessoa pessoa = pessoaRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada"));

        Endereco endereco = EnderecoConverter.toEntity(data);
        endereco.setPessoa(pessoa);
        endereco.setIsPrincipal(pessoa.getEnderecos().isEmpty());

        Endereco novoEndereco = enderecoRepository.save(endereco);

        return EnderecoConverter.toDTO(novoEndereco);
	}

	@Override
	public EnderecoDTO update(Long idEndereco, Long idPessoa, EnderecoDTO data) {

		Endereco endereco = enderecoRepository.findById(idEndereco)
				.orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado"));


        validateAddressAssociation(endereco, idPessoa);
        
		endereco.setLogradouro(data.logradouro());
		endereco.setCep(data.cep());
		endereco.setNumero(data.numero());
		endereco.setCidade(data.cidade());
		endereco.setEstado(data.estado());

		Endereco updatedEndereco = enderecoRepository.save(endereco);

        return EnderecoConverter.toDTO(updatedEndereco);
	}

	@Override
	public void setEnderecoPrincial(Long idPessoa, Long idEndereco) {
		Endereco endereco = enderecoRepository.findById(idEndereco)
				.orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado"));

		validateAddressAssociation(endereco, idPessoa);
		
		enderecoRepository.updateEnderecoPrincipal(idPessoa, idEndereco);
	}

	@Override
	public EnderecoDTO findById(Long id) {
		
		Endereco endereco = enderecoRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado"));
		
		return EnderecoConverter.toDTO(endereco);
	}

	@Override
	public List<EnderecoDTO> findAll() {
        List<Endereco> enderecos = enderecoRepository.findAll();

        return EnderecoConverter.toListDTO(enderecos);
	}
	
	 private void validateAddressAssociation(Endereco endereco, Long idPessoa) {
        if (!endereco.getPessoa().getId().equals(idPessoa)) {
            throw new ResourceNotFoundException("Endereço diferente!");
        }
    }
}
