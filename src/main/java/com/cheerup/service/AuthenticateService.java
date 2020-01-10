package com.cheerup.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cheerup.entity.User;
import com.cheerup.repository.UserRepository;

@Service
public class AuthenticateService {

	@Autowired
	UserRepository loginRepository;
	
	public User loginUser(User userdto) {
		Optional<User> userOptional = loginRepository.findByUsernameAndPassword(userdto.getUsername(), userdto.getPassword());
		if(userOptional.isPresent()) {
			userOptional.get().setPassword("");
			return userOptional.get();
		}
		
		return null;
	}
	
	public void signUpUser(User user) {
		loginRepository.save(user);
	}
}
