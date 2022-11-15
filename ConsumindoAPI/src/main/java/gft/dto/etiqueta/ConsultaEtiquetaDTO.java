package gft.dto.etiqueta;

import java.util.List;

import gft.entities.Usuario;

public class ConsultaEtiquetaDTO {

	private Long id;
	private String nome;
	private List<Usuario> usuarios;

	public ConsultaEtiquetaDTO() {}

	public ConsultaEtiquetaDTO(Long id, String nome, List<Usuario> usuarios) {
		this.id = id;
		this.nome = nome;
		this.usuarios = usuarios;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
}
