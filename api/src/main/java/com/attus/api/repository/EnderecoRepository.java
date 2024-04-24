package com.attus.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.attus.api.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	@Modifying
	@Query("""
			UPDATE Endereco e 
			SET e.isPrincipal = CASE WHEN e.id = :idEndereco THEN true ELSE false END 
			WHERE e.pessoa.id = :idPessoa
			""")
	void updateEnderecoPrincipal(Long idPessoa, Long idEndereco);
}
