package com.g3.order.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// Indicates that any properties not bound in this type should be ignored.
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String name;
    private String phone;
    private String email;
    
    public User() {
	}
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
