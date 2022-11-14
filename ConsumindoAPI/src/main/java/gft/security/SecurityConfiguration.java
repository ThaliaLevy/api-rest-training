package gft.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import gft.filter.FiltroAutenticacao;
import gft.services.AutenticacaoService;
import gft.services.UsuarioService;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)	//habilita a anotação @PreAuthorize nos controllers
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	private final UsuarioService usuarioService;
	private final AutenticacaoService autenticacaoService;
	
	public SecurityConfiguration(@Lazy UsuarioService usuarioService, @Lazy AutenticacaoService autenticacaoService) {
		this.usuarioService = usuarioService;
		this.autenticacaoService = autenticacaoService;
	}
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}

	/*TODO: swagger não pode ser visualizado devido a autenticação, que precisa ser feita antes.
	 * Não está mostrando login, somente 'acesso negado'. */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			//.antMatchers(HttpMethod.GET, "swagger-ui.html#").permitAll()
		    .antMatchers(HttpMethod.POST, "/auth").permitAll()
		    .anyRequest().authenticated()
		    .and().csrf().disable()
		    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		    .and().addFilterBefore(new FiltroAutenticacao(autenticacaoService, usuarioService), UsernamePasswordAuthenticationFilter.class);
	}
}
