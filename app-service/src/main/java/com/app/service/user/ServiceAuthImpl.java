package com.app.service.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.entity.AppRole;
import com.app.entity.AppUser;
import com.app.entity.AuthenticationResponse;
import com.app.entity.UserLogin;
import com.app.entity.UserVisite;
import com.app.repository.UserRepository;
import com.app.security.jwt.JwtProvider;
import com.app.services.ServiceAuth;

@Service
public class ServiceAuthImpl implements ServiceAuth{

	@Autowired
	private UserRepository repo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtProvider jwtProvider;
	
	
	@Override
	@Transactional
	public String signup(UserLogin user) {
		AppUser appUser = new AppUser();
		appUser.setUsername(user.getUsername());
		String password = passwordEncoder.encode(user.getPassword());
		appUser.setPassword(password);
		appUser.setRole(user.getRole());
		appUser.setActive(true);
		if(user.getRole() == null)
		appUser.setRole(AppRole.ROLE_USER);
		repo.save(appUser);
		return "hello monsieur"+user.getUsername();
	}

	@Override
	public AuthenticationResponse login(UserVisite user) {
		   String username = user.getUsername();
		   Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, user.getPassword()));
	      SecurityContextHolder.getContext().setAuthentication(authentication);
	      String name = SecurityContextHolder.getContext().getAuthentication().getName();
	      AppUser appUser = repo.findByUsername(username)
	    		  .orElseThrow(()-> new UsernameNotFoundException("this username is invalid"));
	      List<GrantedAuthority> authority = List.of(new SimpleGrantedAuthority(appUser.getRole().name()));
	      Map<String, Object> authorities = new HashMap<String,Object>();
	      authority.forEach(auth -> authorities.put("authority", auth.getAuthority()));
	      
	      String token = jwtProvider.createToken(authorities, username);
		   return new AuthenticationResponse(token,name);
	}
	
	
	@Override
	public void deleteUser(Long id) {
		AppUser user = repo.findById(id).orElseThrow(()-> new UsernameNotFoundException("this id est invalide"));
		repo.delete(user);
	}

}
