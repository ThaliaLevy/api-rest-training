package gft.dto.etiqueta;

public class RegistroEtiquetaDTO {

	private String nome;
	//private Usuario usuario; 
	
	public RegistroEtiquetaDTO() {}

	public RegistroEtiquetaDTO(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
