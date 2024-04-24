<h1 align="center">
  Teste Attus - Java
</h1>

**Desafio:** desenvolver uma nova funcionalidade de gerenciamento de pessoas, da apresentação da proposta inicial a entrega final do código. Será o digrama de classes, e o código fonte da funcionalidade.

## Tecnologias
- Java 17
- Database H2
- Flyway
- Spring Boot 3
- Spring MVC
- Spring Data JPA
- Java Bean Validation
- Lombok

## Como iniciar o projeto

- Clone repositório git:
```
git clone https://github.com/vinidanielp/attus_test.git
```
- Executar a aplicação Spring Boot.
- Acessar aplicação em `http://localhost:8080`.
- Na raiz do diretorio desse projeto contém um arquivo com nome Insomnia-Postman.json, nele contém as requisições para serem testadas, funciona a importação tanto no Insomnia e no Postman.


## Diagrama de Classes

```mermaid
classDiagram
    class Endereco {
        + Long id
        + String logradouro
        + String cep
        + String numero
        + String cidade
        + String estado
        + Boolean isPrincipal
        + Pessoa pessoa
    }

    class Pessoa {
        + Long id
        + String nomeCompleto
        + LocalDate dataNascimento
        + List<Endereco> enderecos
    }

    class AddressDTO {
        + Long id
        + String logradouro
        + String cep
        + String numero
        + String cidade
        + String estado
        + Boolean isPrincipal
    }

    class PersonDTO {
        + Long id
        + String nomeCompleto
        + LocalDate dataNascimento
        + List<EnderecoDTO> enderecos
    }

    class PessoaService {
        --
        + create(PessoaDTO data)
        + update(Long id, PessoaDTO data)
        + findById(Long id)
        + findAll()
    }

    class EnderecoService {
        --
        + create(Long id, EnderecoDTO data)
        + update(Long idEndereco, Long idPessoa, EnderecoDTO data)
        + setEnderecoPrincial(Long idPessoa, Long idEndereco)
        + findById(Long id)
        + findAll()
    }

    class EnderecoServiceImpl {
        - EnderecoRepository enderecoRepository
        - PessoaRepository pessoaRepository
        --
        + create(Long id, EnderecoDTO data)
        + update(Long idEndereco, Long idPessoa, EnderecoDTO data)
        + setDefaultAddress(Long idPessoa, Long idEndereco)
        + findById(Long id)
        + findAll()
        + validarEnderecoAssociado(Address endereco, Long idPessoa)
    }

    class PessoaServiceImpl {
        - PessoaRepository pessoaRepository
        --
        + create(PessoaDTO data)
        + update(Long id, PessoaDTO data)
        + findById(Long id)
        + findAll()
    }

    class EnderecoRepository {
        + updateEnderecoPrincipal(Long idPessoa, Long idEndereco)
    }

    class EnderecoConverter {
        + toDTO(Endereco endereco)
        + toEntity(EnderecoDTO dto)
        + toListDTO(List<Endereco> enderecos)
        + toEntityList(List<EnderecoDTO> enderecosDTO)
    }

    class PessoaConverter {
        + toEntity(PessoaDTO dto)
        + toListDTO(List<Pessoa> pessoas)
        + toDTO(Pessoa pessoa)
    }

    class PessoaController {
        - PessoaService pessoaService
        --
        + create(PessoaDTO data)
        + update(Long id, PessoaDTO data)
        + findById(Long id)
        + findAll()
    }

    class EnderecoController {
        - EnderecoService enderecoService
        --
        + create(Long id, EnderecoDTO data)
        + update(Long idEndereco, Long idPessoa, EnderecoDTO data)
        + setEnderecoPrincipal(Long idPessoa, Long idEndereco)
        + findById(Long id)
        + findAll()
    }

    EnderecoController --> EndereoService
    PessoaController --> PessoaService

    PessoaController --> PessoaDTO : Uses
    EnderecoController --> EnderecoDTO : Uses

    PessoaDTO --> EnderecoDTO : Contains

    GlobalExceptionHandler --> ResourceNotFoundException
    GlobalExceptionHandler --> EnderecoNaoAssociadoException

    Pessoa "1" --> "*" Endereco
    Endereco "0..*" --> "1" Pessoa

    EnderecoConverter --> Endereco
    EnderecoConverter --> EnderecoDTO

    PessoaConverter --> Pessoa
    PessoaConverter --> PessoaDTO
