package com.cheerup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cheerup.Repository.MomentRepository;
import com.cheerup.Repository.UserRepository;
import com.cheerup.dto.UserMoment;
import com.cheerup.entity.Moment;
import com.cheerup.entity.User;

@Service
public class UserMomentService {
	
	@Autowired
	MomentRepository momentRepository;
	
	
	@Autowired
	UserRepository userRepository;
	
	public void saveUserMoment(UserMoment userMoment) {
		
		User user = userRepository.findByUsername(userMoment.getUsername());
		
		//TODO : If user is null throw exception
		
		Moment moment = userMoment.getMoment();
		momentRepository.save(moment);
		List<Moment> moments = user.getMoments();
		moments.add(moment);
		userRepository.save(user);
		
	}

}
