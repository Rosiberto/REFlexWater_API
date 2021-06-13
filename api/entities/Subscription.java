package com.api.entities;

public class Subscription {

	private String id;
	private String description;
	private String status;
	
	public Subscription(String id, String description, String status) {
		super();
		this.id = id;
		this.description = description;
		this.status = status;
	}

	public Subscription() {
		
	}

	public String getId() {
		return id;
	}

	
	public void setId(String id) { this.id = id; }

	public String getDescription() { return description; }

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {	return status; }

	public void setStatus(String status) { this.status = status; }
	
}

