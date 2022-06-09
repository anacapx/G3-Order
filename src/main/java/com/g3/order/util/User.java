package com.g3.order.util;

import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Indicates that any properties not bound in this type should be ignored.
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    private String name;
    private String phone;
		@Email(message="O campo EMAIL deve ser preenchido corretamente.")
    private String email;
}
