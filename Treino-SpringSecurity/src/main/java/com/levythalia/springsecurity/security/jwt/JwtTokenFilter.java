package com.levythalia.springsecurity.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class JwtTokenFilter extends GenericFilterBean {

	private final JwtTokenProvider tokenProvider;
	
	public JwtTokenFilter(JwtTokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}
	
	//filtro será executado a cada requisição
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//obtem o token a partir da request
		String token = tokenProvider.resolveToken((HttpServletRequest) request);
		//valida o token
		if(token != null && tokenProvider.validateToken(token)) {
			//obtem autenticação
			Authentication auth = tokenProvider.getAuthentication(token);
			
			if(auth != null) {
				//caso obtenha a autenticação, a seta para o servidor na sessão do spring
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		chain.doFilter(request, response);
	}
}
