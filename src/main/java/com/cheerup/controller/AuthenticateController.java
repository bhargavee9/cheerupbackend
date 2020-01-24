package com.cheerup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.cheerup.constant.ErrorMessage;
import com.cheerup.entity.Moment;
import com.cheerup.entity.User;
import com.cheerup.exception.NotFoundException;
import com.cheerup.exception.UserExistsException;
import com.cheerup.repository.UserRepository;
import com.cheerup.service.AuthenticateService;
import com.cheerup.service.MomentService;

@CrossOrigin(origins = {"https://myjournal9.herokuapp.com","http://localhost:4200"}, maxAge = 3600)
@RestController
@RequestMapping(value = "/")
public class AuthenticateController {
	
	@Autowired
	AuthenticateService authenticateService;
	
	@Autowired
	MomentService userMomentService;
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/")
	public String test(){
	    return "Hello world !!!";
	}
	
	@PostMapping("login")
	public User getUser(@RequestBody User userdto) throws NotFoundException {
		
	   User userData = authenticateService.loginUser(userdto);
	   if(userData == null) {
			throw new NotFoundException(ErrorMessage.CHEERUP002.name());
		   
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
	
	@PostMapping("{username}/moment")
   	public RedirectView addMoment(@PathVariable String username , @RequestBody Moment userMoment) throws NotFoundException {

		   userMomentService.saveUserMoment(username,userMoment);
		   RedirectView redirectView = new RedirectView();
		   redirectView.setUrl("/success");
		   return redirectView;
		
	 }
	
	@PutMapping("{username}/moment/{momentID}")
   	public RedirectView updateMoment(@PathVariable String username,@RequestBody Moment userMoment, @PathVariable("momentID") String momentID) throws NotFoundException {

		   userMomentService.updateMoment(username,userMoment, momentID);
		   RedirectView redirectView = new RedirectView();
		   redirectView.setUrl("/success");
		   return redirectView;
		
	 }
	
	@GetMapping("{username}/moment")
   	public Moment getMoment(@PathVariable String username) throws NotFoundException {

		  Moment moment = userMomentService.getRandomMoment(username);
		  return moment;
		
	 }
}
