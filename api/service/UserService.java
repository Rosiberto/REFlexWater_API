package com.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entities.Usuario;
import com.api.repository.IUserRepository;


@Service
public class UserService implements IUserService{

	@Autowired
	private IUserRepository userRepositorio;
	
	
	public void saveUser(Usuario user) {
		userRepositorio.save(user);
	}
	
	public Usuario getLogin(String email) {
		return userRepositorio.findByEmail(email);
	}
}
