package com.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.api.entities.Usuario;

public interface IUserRepository extends MongoRepository<Usuario, String>{

	Usuario findByEmail(String email);
		
	
}
