package gft.dto.usuario;

public class RegistroUsuarioDTO {

	private String email;
	private String senha;
	private Long perfilId;
	private Long filialId;

	public RegistroUsuarioDTO() {}

	public RegistroUsuarioDTO(String email, String senha, Long perfilId, Long filialId) {
		this.email = email;
		this.senha = senha;
		this.perfilId = perfilId;
		this.filialId = filialId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Long getPerfilId() {
		return perfilId;
	}

	public void setPerfilId(Long perfilId) {
		this.perfilId = perfilId;
	}

	public Long getFilialId() {
		return filialId;
	}

	public void setFilialId(Long filialId) {
		this.filialId = filialId;
	}
}
