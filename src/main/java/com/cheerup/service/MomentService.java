package com.cheerup.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cheerup.constant.ErrorMessage;
import com.cheerup.entity.Moment;
import com.cheerup.entity.User;
import com.cheerup.exception.NotFoundException;
import com.cheerup.repository.MomentRepository;
import com.cheerup.repository.UserRepository;

@Service
public class MomentService {
	
	@Autowired
	MomentRepository momentRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public void saveUserMoment(String username , Moment moment) throws NotFoundException {
		
		User user = userRepository.findByUsername(username);
		
		if(user == null) {
			throw new NotFoundException(ErrorMessage.CHEERUP002.name());
		}
		
		momentRepository.save(moment);
		List<Moment> moments = user.getMoments();
		if(moments == null) {
			moments = new ArrayList<>();
			user.setMoments(moments);
		}
		moments.add(moment);
		userRepository.save(user);
		
	}

	public void updateMoment(String username, Moment newMoment, String momentID) throws NotFoundException {

		User user = userRepository.findByUsername(username);
		if(user == null) {
			throw new NotFoundException(ErrorMessage.CHEERUP002.name());
		}
	    
        List<Moment> oldMoments = user.getMoments();
        Boolean found = false;

        if(oldMoments != null) {
           for(Moment moment : oldMoments) {
        	 if(moment.get_id().equals(momentID))
        	  {
        		found = true;
        		if(newMoment!=null) {
        		       moment.setDescription(newMoment.getDescription());
        		       moment.setFeeling(newMoment.getFeeling());
        		       momentRepository.save(moment);
        		}
        		else {
        			momentRepository.delete(moment);
        		}
        	 }
        }
        }
        
		if(oldMoments == null || !found) {
			throw new NotFoundException(ErrorMessage.CHEERUP002.name());
		}
		
	}

	public Moment getRandomMoment(String username) throws NotFoundException {
		User user = userRepository.findByUsername(username);
		if(user == null) {
			throw new NotFoundException(ErrorMessage.CHEERUP002.name());
		}
		if(user.getMoments() == null) {
			throw new NotFoundException(ErrorMessage.CHEERUP003.name());

		}
		int numberofMoments = user.getMoments().size();
		
		Random rand = new Random();
		int randomNum = rand.nextInt(numberofMoments);
		return user.getMoments().get(randomNum);
	}
	
	
	

}
