package com.app.services;

import com.app.entity.AuthenticationResponse;
import com.app.entity.UserLogin;
import com.app.entity.UserVisite;

public interface ServiceAuth {

	public String signup(UserLogin user);
	public AuthenticationResponse login(UserVisite user);
	public void deleteUser(Long id);
}
