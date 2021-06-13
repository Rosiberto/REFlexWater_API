package com.api.entities;

import java.util.List;

public class Entities {
	String id;
	String type;
	List<Attributes> attributes;
	String statusCode;
	
	public Entities() {	}
	
	public Entities(String id, String type, List<Attributes> attributes, String statusCode) {
		super();
		this.id = id;
		this.type = type;
		this.attributes = attributes;
		this.statusCode = statusCode;
	}

	public String getId() { return id; }
	public void setId(String id) { this.id = id; }
	public String getType() { return type; }
	public void setType(String type) { this.type = type; }
	public List<Attributes> getAttributes() { return attributes; }
	public void setAttributes(List<Attributes> attributes) { this.attributes = attributes; }
	public String getStatusCode() { return statusCode; }
	public void setStatusCode(String statusCode) { this.statusCode = statusCode; }

}

/*    		  "id": "Beto1",
    		  "type": "Beto",
    		  "pressao": 123,
    		  "temperature": 279

*/