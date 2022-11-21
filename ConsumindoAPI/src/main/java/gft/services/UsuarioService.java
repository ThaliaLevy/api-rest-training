package gft.services;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import gft.entities.Usuario;
import gft.exception.AlreadyReportedException;
import gft.exception.BadRequestException;
import gft.repositories.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

	private final UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}	

	public Usuario salvarUsuario(Usuario usuario) {		
		try {
			usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
			
			if(buscarUsuarioPorEmail(usuario.getEmail()) == null) {
				return usuarioRepository.save(usuario);
			}
			
			throw new AlreadyReportedException("E-mail não está disponível. Insira um diferente.");
		} catch (DataIntegrityViolationException e) {
			String campoQueCausouException = tratarCampoQueCausouException(e.getMostSpecificCause() + "");
						
			throw new BadRequestException(campoQueCausouException + " deve ser informado!");
		} catch (IllegalArgumentException e) {
			throw new BadRequestException("senha deve ser informada!");
		}
	}
	
	public String tratarCampoQueCausouException(String campoQueCausouException) {		
		return campoQueCausouException = campoQueCausouException.replace("_i", "I")
							   		   						    .substring(campoQueCausouException.indexOf("'") +1, 
							   		   								   	   campoQueCausouException.lastIndexOf("'"));
	}

	public Usuario buscarUsuarioPorEmail(String email) {
		Optional<Usuario> optional = usuarioRepository.findByEmail(email);
		
		if(optional.isEmpty()) {
			return null;
		}
		
		return optional.get();		
	}

	public Usuario buscarUsuarioPorId(Long idUsuario) {
		Optional<Usuario> optional = usuarioRepository.findById(idUsuario);

		if (optional.isEmpty()) {
			throw new RuntimeException("Usuário não encontrado!");
		}
		
		return optional.get();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails userDetails = buscarUsuarioPorEmail(username);
		
		if(buscarUsuarioPorEmail(username) == null) {
			throw new UsernameNotFoundException("Usuário não encontrado!");
		}
		
		return userDetails;
	}
}
