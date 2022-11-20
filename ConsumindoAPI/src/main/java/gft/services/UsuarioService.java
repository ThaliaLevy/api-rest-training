package gft.services;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import gft.entities.Usuario;
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
			return usuarioRepository.save(usuario);
		} catch (DataIntegrityViolationException e) {
			String campoQueCausouException = e.getMostSpecificCause() + "";
			throw new BadRequestException(campoQueCausouException.substring(
										  campoQueCausouException.indexOf("'") +1, 
										  campoQueCausouException.lastIndexOf("'")) + " deve ser informado!");
		} catch (IllegalArgumentException e) {
			//acho que precisa do tratamento na outra classe > de autenticação
			throw new BadRequestException("senha deve ser informada!");
		}
		
	/*	if(usuario.getNome().isEmpty()) {
			throw new BadRequestException(usuario.getNome() + " é um campo obrigatório.");
		} else if(usuario.getEmail().isEmpty()) {
			throw new BadRequestException(usuario.getNome() + " é um campo obrigatório.");
		} else if(usuario.getSenha().isEmpty()) {
			
		} else if(usuario.getPerfil().getNome() == null) {
			
		}
		
		*/
	}

	public Usuario buscarUsuarioPorEmail(String email) {
		Optional<Usuario> optional = usuarioRepository.findByEmail(email);

		if (optional.isEmpty()) {
			throw new UsernameNotFoundException("Usuário não encontrado!");
		}
		return optional.get();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return buscarUsuarioPorEmail(username);
	}

	public Usuario buscarUsuarioPorId(Long idUsuario) {
		Optional<Usuario> optional = usuarioRepository.findById(idUsuario);

		if (optional.isEmpty()) {
			throw new RuntimeException("Usuário não encontrado!");
		}
		return optional.get();
	}
}
