package com.attus.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "O ID do endereço fornecido não está associado ao ID da pessoa especificada!")
public class EnderecoNaoAssociadoException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public EnderecoNaoAssociadoException(String message){
        super(message);
    }

}
