package com.cheerup.dto;

import com.cheerup.entity.Moment;

public class UserMoment {
	
	String username;
	Moment moment;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Moment getMoment() {
		return moment;
	}
	public void setMoment(Moment moment) {
		this.moment = moment;
	}
	
	

}
