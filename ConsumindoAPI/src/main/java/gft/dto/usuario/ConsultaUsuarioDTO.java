package gft.dto.usuario;

public class ConsultaUsuarioDTO {

	private String nome;
	private String email;
	private String nomePerfil;

	public ConsultaUsuarioDTO() {}

	public ConsultaUsuarioDTO(String nome, String email, String nomePerfil) {
		this.nome = nome;
		this.email = email;
		this.nomePerfil = nomePerfil;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomePerfil() {
		return nomePerfil;
	}

	public void setNomePerfil(String nomePerfil) {
		this.nomePerfil = nomePerfil;
	}
}
