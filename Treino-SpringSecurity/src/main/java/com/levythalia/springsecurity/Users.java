package com.levythalia.springsecurity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "tb_users")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String email;
	private String nome;
	private String senha;
	private String contaNaoExpirada;
	private String contaNaoBloqueada;
	private String credenciaisNaoExpiradas;
	private String enabled;
	
	@ManyToOne
	private Permission permission;

	public Users() {}
	
	public Users(Long id, String email, String nome, String senha, String contaNaoExpirada, String contaNaoBloqueada,
			String credenciaisNaoExpiradas, String enabled, Permission permission) {
		this.id = id;
		this.email = email;
		this.nome = nome;
		this.senha = senha;
		this.contaNaoExpirada = contaNaoExpirada;
		this.contaNaoBloqueada = contaNaoBloqueada;
		this.credenciaisNaoExpiradas = credenciaisNaoExpiradas;
		this.enabled = enabled;
		this.permission = permission;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getContaNaoExpirada() {
		return contaNaoExpirada;
	}

	public void setContaNaoExpirada(String contaNaoExpirada) {
		this.contaNaoExpirada = contaNaoExpirada;
	}

	public String getContaNaoBloqueada() {
		return contaNaoBloqueada;
	}

	public void setContaNaoBloqueada(String contaNaoBloqueada) {
		this.contaNaoBloqueada = contaNaoBloqueada;
	}

	public String getCredenciaisNaoExpiradas() {
		return credenciaisNaoExpiradas;
	}

	public void setCredenciaisNaoExpiradas(String credenciaisNaoExpiradas) {
		this.credenciaisNaoExpiradas = credenciaisNaoExpiradas;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}
}
