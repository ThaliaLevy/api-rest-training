package gft.dto.usuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import gft.dto.filial.FilialMapper;
import gft.entities.Filial;
import gft.entities.Perfil;
import gft.entities.Usuario;

public class UsuarioMapper {

	public static Usuario fromDTO(RegistroUsuarioDTO dto) {
		Perfil perfil = new Perfil();
		perfil.setId(dto.getPerfilId());	//perfil deveria ser verificado se existe no banco de dados
		
		Filial filial = new Filial();
		filial.setId(dto.getFilialId());
		
		return new Usuario(null, dto.getEmail(), new BCryptPasswordEncoder().encode(dto.getSenha()), perfil, filial);
	}
	
	public static ConsultaUsuarioDTO fromEntity(Usuario usuario) {
		return new ConsultaUsuarioDTO(usuario.getEmail(), usuario.getPerfil().getNome(), FilialMapper.fromEntity(usuario.getFilial()));
	}
}
