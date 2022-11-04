package gft.dto.cliente;

import gft.dto.endereco.EnderecoDTO;

public class RegistroClienteDTO {

	private String cpf;
	private String nome;
	private String telefone;
	private String email;
	private EnderecoDTO endereco;
	
	public RegistroClienteDTO() {}

	public RegistroClienteDTO(String cpf, String nome, String telefone, String email, EnderecoDTO endereco) {
		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public EnderecoDTO getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoDTO endereco) {
		this.endereco = endereco;
	}
}
