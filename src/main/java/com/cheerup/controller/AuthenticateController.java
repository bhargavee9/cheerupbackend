package com.cheerup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.cheerup.Repository.UserRepository;
import com.cheerup.dto.UserMoment;
import com.cheerup.entity.User;
import com.cheerup.exception.UserExistsException;
import com.cheerup.exception.UserNotFoundException;
import com.cheerup.service.AuthenticateService;
import com.cheerup.service.UserMomentService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(value = "/")
public class AuthenticateController {
	
	@Autowired
	AuthenticateService authenticateService;
	
	@Autowired
	UserMomentService userMomentService;
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/")
	public String test(){
	    return "Hello world !!!";
	}
	
	@PostMapping("login")
	public User getUser(@RequestBody User userdto) throws UserNotFoundException {
		
	   User userData = authenticateService.loginUser(userdto);
	   if(userData == null) {
		   throw new UserNotFoundException("User "+userdto.getUsername() + " doesnot exist ");
		   
	   }
	  return userData; 
	}
	
	
	@PostMapping("signup")
	public RedirectView addUser(@RequestBody User userdto) throws UserExistsException{
		   User userData = authenticateService.loginUser(userdto);
           if(userData!=null) {
        	   throw new UserExistsException("User "+userdto.getUsername() + " already exists");
           }
           authenticateService.signUpUser(userdto);
           RedirectView redirectView = new RedirectView();
		   redirectView.setUrl("success");
		   return redirectView;
	 }
	
	@PostMapping("addmoment")
   	public RedirectView addMoment(@RequestBody UserMoment userMoment) {

		   userMomentService.saveUserMoment(userMoment);
		   RedirectView redirectView = new RedirectView();
		   redirectView.setUrl("success");
		   return redirectView;
		
	 }
}
