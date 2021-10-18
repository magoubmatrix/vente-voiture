package com.app.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.entity.AppUser;
import com.app.repository.UserRepository;




@Service
public class MyUserService implements UserDetailsService {

	@Autowired
	private UserRepository repo;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			AppUser appUser = repo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("le username %s est invalide", username)));
		return new User(username, appUser.getPassword(),getAuthorities(appUser));
	}

	private Collection<? extends GrantedAuthority> getAuthorities(AppUser user){
		String role = user.getRole().name();
		return List.of(new SimpleGrantedAuthority(role));
	}


}

