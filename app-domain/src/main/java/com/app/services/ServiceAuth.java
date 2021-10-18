package com.app.services;

import com.app.entity.AuthenticationResponse;
import com.app.user.UserSignUp;
import com.app.user.UserVisite;

public interface ServiceAuth {

	public String signup(UserSignUp user);
	public AuthenticationResponse login(UserVisite user);
	public void activeAccount(String token);
	public void deleteUser(Long id);
}
