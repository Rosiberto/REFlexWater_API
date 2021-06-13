package com.api.service;

import com.api.entities.Usuario;

public interface IUserService {

	public void saveUser(Usuario user);
	
	public Usuario getLogin(String email);
}
