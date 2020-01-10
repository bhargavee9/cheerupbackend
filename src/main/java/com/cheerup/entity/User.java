package com.cheerup.entity;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("user")
public class User implements Serializable {

    /**
	 * 
	 */
	public static final long serialVersionUID = 1L;
	
	@Id
	@Field("_id")
	public String  username;
	public String password;
    
	@DBRef
	List<Moment> moments;
     
    public User(String username, String password){
    	super();
    	this.username = username;
    	this.password = password;
    }
    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Moment> getMoments() {
		return moments;
	}

	public void setMoments(List<Moment> moments) {
		this.moments = moments;
	}
	
	
	
}