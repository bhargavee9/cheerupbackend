package com.cheerup.constant;

public enum ErrorMessage {
	CHEERUP000("Success"),
	CHEERUP001("Error creating user ! User already exists"),
	CHEERUP002("Error logging in ! User does not exist"),
	CHEERUP003("No moments to show. Please enter a moment.");

	String message;

	private ErrorMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	

}
