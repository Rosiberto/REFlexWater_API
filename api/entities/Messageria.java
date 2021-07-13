package com.api.entities;

public class Messageria {

	private String severit;
	private String message;
	
	public Messageria(String severit, String message) {
		super();
		this.severit = severit;
		this.message = message;
	}	
	
	public Messageria() {		
	}

	public String getSeverit() {
		return severit;
	}

	public void setSeverit(String severit) {
		this.severit = severit;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Messageria [severit=" + severit + ", message=" + message + "]";
	}	
}
