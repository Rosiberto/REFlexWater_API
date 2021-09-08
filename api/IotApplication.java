package com.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.api.*", 
//							   "com.api.messageria"
//							   "com.api.controller",					   
//							   "com.api.service", 
//							   "com.api.repository",
//							   "com.api.entities",
//							   "com.api.util"})
							   })
@EnableMongoRepositories("com.api.repository")
public class IotApplication {
	
	public static final Logger logger = LoggerFactory.getLogger("com.api.iot");

	
	
	public static void main(String[] args) {
		SpringApplication.run(IotApplication.class, args);
	}

}
