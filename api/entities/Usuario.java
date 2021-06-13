package com.api.entities;


import java.util.Collection;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



@Document(collection = "user")
public class Usuario implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    private String id;	
	private String nome;	
	private String email;
	private String senha;
/*	private Collection<GrantedAuthority> roles;
	
	public Usuario(String id, String nome, String email, String senha, Collection<GrantedAuthority> roles) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.roles = roles;
	}
*/
	public Usuario() {}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
/*
	public Collection<GrantedAuthority> getRoles() {
		return roles;
	}

	public void setRoles(Collection<GrantedAuthority> p) {
		this.roles = p;
	}
*/
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return null;//(Collection<? extends GrantedAuthority>) this.roles;
	}

	@Override
	public String getPassword() {
		
		return this.senha;
	}

	@Override
	public String getUsername() {
	
		return this.nome;
	}

	@Override
	public boolean isAccountNonExpired() {
	
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
	
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
	
		return true;
	}

	@Override
	public boolean isEnabled() {
	
		return true;
	}
}
