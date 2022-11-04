package gft.dto.cliente;

import gft.dto.endereco.EnderecoDTO;

public class ConsultaClienteDTO {

	private Long id;
	private String cpf;
	private String nome;
	private String telefone;
	private String email;
	private EnderecoDTO endereco;

	public ConsultaClienteDTO() {}

	public ConsultaClienteDTO(Long id, String cpf, String nome, String telefone, String email, EnderecoDTO endereco) {
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
