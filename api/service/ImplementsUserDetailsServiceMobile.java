package com.api.service;


import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.api.entities.Usuario;
import com.api.repository.IUserRepository;


@Repository
@Transactional
public class ImplementsUserDetailsServiceMobile implements UserDetailsService{

	@Autowired
	private IUserRepository ur;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Usuario usuario = ur.findByEmail(email);
			
		
		if(usuario == null){
			throw new UsernameNotFoundException("Usuário não encontrado!");
		}
		
		List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ADMIN"));
		
		System.out.println(usuario.getUsername() +" " +usuario.getEmail() +" " +usuario.getPassword());
		
		return new User(usuario.getUsername(), 
						usuario.getPassword(), 
						authorities);

	}
}
