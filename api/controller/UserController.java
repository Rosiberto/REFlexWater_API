package com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.api.entities.Usuario;
import com.api.service.UserService;

import springfox.documentation.annotations.ApiIgnore;


@Controller
@RequestMapping(value = "/")
@ApiIgnore
public class UserController {

	@Autowired
	private UserService userService;
	
		
	@RequestMapping(value = "user", method = RequestMethod.POST)
	public String save(@RequestParam("nome") String nome, @RequestParam("email") String email, @RequestParam("senha") String senha) {
		
		System.out.println(nome +" " + senha +" "+email);
		
		Usuario user = new Usuario();
		
		user.setEmail(email);
		user.setNome(nome);
		user.setSenha( new BCryptPasswordEncoder().encode(senha) );
		
		//System.out.println(user.getEmail() +" " + user.getSenha());
		
		userService.saveUser(user);

		//return "success";
		return "login";		
	}
	
	@RequestMapping(value = "userMobile", method = RequestMethod.POST)
	public String saveMobile(@RequestParam("nome") String nome, @RequestParam("email") String email, @RequestParam("senha") String senha) {
		
		System.out.println(nome +" " + senha +" "+email);
		
		Usuario user = new Usuario();
		
		user.setEmail(email);
		user.setNome(nome);
		user.setSenha( new BCryptPasswordEncoder().encode(senha) );
		
		//System.out.println(user.getEmail() +" " + user.getSenha());
		
		userService.saveUser(user);

		//return "success";
		return "mobile/login";		
	}

	
}
