package com.app.user;

import com.app.entity.AppRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserSignUp {

	
	private String username;
	private String email;
	private String password;
	private AppRole role;
}
