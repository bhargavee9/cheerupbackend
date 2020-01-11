package com.cheerup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cheerup.dto.UserMoment;
import com.cheerup.entity.Moment;
import com.cheerup.entity.User;
import com.cheerup.exception.NotFoundException;
import com.cheerup.repository.MomentRepository;
import com.cheerup.repository.UserRepository;

@Service
public class UserMomentService {
	
	@Autowired
	MomentRepository momentRepository;
	
	
	@Autowired
	UserRepository userRepository;
	
	public void saveUserMoment(UserMoment userMoment) throws NotFoundException {
		
		User user = userRepository.findByUsername(userMoment.getUsername());
		
		if(user == null) {
			throw new NotFoundException("User "+ userMoment.getUsername()+ " not present");
		}
		
		Moment moment = userMoment.getMoment();
		momentRepository.save(moment);
		List<Moment> moments = user.getMoments();
		moments.add(moment);
		userRepository.save(user);
		
	}

	public void updateMoment(UserMoment userMoment, String momentID) throws NotFoundException {

		User user = userRepository.findByUsername(userMoment.getUsername());
		if(user == null) {
			throw new NotFoundException("User "+ userMoment.getUsername()+ " not present");
		}
	    
		Moment newMoment = userMoment.getMoment();
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
			throw new NotFoundException(" Moment with ID "+newMoment.get_id() + " not found");
		}
		
	}
	
	
	

}
