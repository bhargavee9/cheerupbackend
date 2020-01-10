package com.cheerup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.cheerup.authenticate.entity","com.cheerup.authenticate.controller","com.cheerup.authenticate.service","com.cheerup.authenticate.exception"})
//@EnableMongoRepositories(repositoryFactoryBeanClass = LoginRepository.class)
public class AuthenticateApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticateApplication.class, args);
	}

	
        
}
