package com.levythalia.springsecurity.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidJwtAuthenticationException extends AuthenticationException{
	
	public InvalidJwtAuthenticationException(String msg) {
		super(msg);
	}

	private static final long serialVersionUID = 1L;

}
