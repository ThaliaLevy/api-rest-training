package gft.services;


import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import gft.controllers.form.AutenticacaoForm;
import gft.dto.TokenDTO;
import gft.entities.Usuario;

@Service
public class AutenticacaoService {
	@Value("${loja.jwt.expiration}")
	private String expiration;
	
	@Value("${loja.jwt.secret}")
	private String secret;
	
	@Value("${loja.jwt.issuer}")
	private String issuer;
	
/*	private final AuthenticationManager authManager;
	
	public AutenticacaoService(AuthenticationManager authManager) {
		this.authManager = authManager;
	}*/

	public TokenDTO autenticar(AutenticacaoForm authForm) throws AuthenticationException {
		//Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(authForm.getEmail(), authForm.getSenha()));
		
		//String token = gerarToken(authentication);
		String token = gerarToken(null);
		token = new BCryptPasswordEncoder().encode("1234");
		
		return new TokenDTO(token);
	}
	
	// é por esse metodo que há o reconhecimento dos tokens
	private Algorithm criarAlgoritmo() {
		return Algorithm.HMAC256(secret);
	}
	
	private String gerarToken(Authentication authentication) {
		//Usuario principal = (Usuario) authentication.getPrincipal();
		Usuario principal = new Usuario();	//ecluir
		principal.setId(1L);
		
		Date dataDeHoje = new Date();
		Date dataDeExpiracao = new Date(dataDeHoje.getTime() + Long.parseLong(expiration));
		return JWT.create()
				  .withIssuer(issuer)
				  .withExpiresAt(dataDeExpiracao)
				  .withSubject(principal.getId().toString())
				  .sign(this.criarAlgoritmo());
	}
}
