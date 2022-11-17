package gft.dto.etiqueta;

import java.util.List;

import gft.entities.Usuario;

public class RegistroEtiquetaDTO {

	private List<Usuario> usuarios;
	private String etiqueta;
	
	public RegistroEtiquetaDTO() {}

	public RegistroEtiquetaDTO(List<Usuario> usuarios, String etiqueta) {
		this.usuarios = usuarios;
		this.etiqueta = etiqueta;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}
}
