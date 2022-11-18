package gft.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import gft.entities.Usuario;
import gft.repositories.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

	private final UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
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

	public Usuario salvarUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
}
