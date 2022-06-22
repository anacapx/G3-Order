package com.g3.order.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// Indicates that any properties not bound in this type should be ignored.
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {
    private String name;
    private String phone;
    private String email;
	public Object getName() {
		// TODO Auto-generated method stub
		return null;
	}
    
    
}
