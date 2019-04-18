package com.thiagoiplinsky.exercise.exceptionHandler;

public class Error {

	public String userMessage;
	public String devMessage;

	public Error(String userMessage, String devMessage) {
		this.userMessage = userMessage;
		this.devMessage = devMessage;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public String getDevMessage() {
		return devMessage;
	}
}