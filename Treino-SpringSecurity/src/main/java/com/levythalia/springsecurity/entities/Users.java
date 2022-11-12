package com.levythalia.springsecurity.entities;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@Table(name = "tb_users")
public class Users implements UserDetails {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "email", unique = true)
	private String email;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "senha")
	private String senha;
	
	@Column(name = "conta_nao_expirada")
	private boolean contaNaoExpirada;
	
	@Column(name = "conta_nao_bloqueada")
	private boolean contaNaoBloqueada;
	
	@Column(name = "credenciais_nao_expiradas")
	private boolean credenciaisNaoExpiradas;
	
	@Column(name = "enabled")
	private boolean enabled;
	
	@ManyToOne
	private Permission permission;

	public Users() {}
	
	public Users(Long id, String email, String nome, String senha, boolean contaNaoExpirada, boolean contaNaoBloqueada,
			boolean credenciaisNaoExpiradas, boolean enabled, Permission permission) {
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

	public boolean isContaNaoExpirada() {
		return contaNaoExpirada;
	}

	public void setContaNaoExpirada(boolean contaNaoExpirada) {
		this.contaNaoExpirada = contaNaoExpirada;
	}

	public boolean isContaNaoBloqueada() {
		return contaNaoBloqueada;
	}

	public void setContaNaoBloqueada(boolean contaNaoBloqueada) {
		this.contaNaoBloqueada = contaNaoBloqueada;
	}

	public boolean isCredenciaisNaoExpiradas() {
		return credenciaisNaoExpiradas;
	}

	public void setCredenciaisNaoExpiradas(boolean credenciaisNaoExpiradas) {
		this.credenciaisNaoExpiradas = credenciaisNaoExpiradas;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.contaNaoExpirada;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.contaNaoBloqueada;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credenciaisNaoExpiradas;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(this.permission);
	}

	@Override
	public int hashCode() {
		return Objects.hash(contaNaoBloqueada, contaNaoExpirada, credenciaisNaoExpiradas, email, enabled, id, nome,
				permission, senha);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Users other = (Users) obj;
		return contaNaoBloqueada == other.contaNaoBloqueada && contaNaoExpirada == other.contaNaoExpirada
				&& credenciaisNaoExpiradas == other.credenciaisNaoExpiradas && Objects.equals(email, other.email)
				&& enabled == other.enabled && Objects.equals(id, other.id) && Objects.equals(nome, other.nome)
				&& Objects.equals(permission, other.permission) && Objects.equals(senha, other.senha);
	}
}
