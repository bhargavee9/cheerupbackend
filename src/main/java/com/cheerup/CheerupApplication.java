package com.cheerup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.cheerup.entity","com.cheerup.controller","com.cheerup.service","com.cheerup.exception"})
//@EnableMongoRepositories(repositoryFactoryBeanClass = LoginRepository.class)
public class CheerupApplication {

	public static void main(String[] args) {
		SpringApplication.run(CheerupApplication.class, args);
	}

	
        
}
