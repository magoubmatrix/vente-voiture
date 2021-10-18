package com.app.controller.user;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.AppUser;
import com.app.entity.AuthenticationResponse;

import com.app.repository.UserRepository;
import com.app.services.ServiceAuth;
import com.app.user.UserSignUp;
import com.app.user.UserVisite;

@RestController
@RequestMapping("/auth")

public class UserController {
	@Autowired
	private ServiceAuth service;
	@Autowired
	private UserRepository repo;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody UserSignUp user) {
	service.signup(user);
	return ResponseEntity.ok("user registration succeffly");
	}
	
	@PostMapping("/visite")
	public  AuthenticationResponse loginUser(@RequestBody UserVisite user)  {
	
		 return service.login(user);
	}
	
	@GetMapping("/verificationAccount/{token}")
	public ResponseEntity<String> activeAccount(@PathVariable String token){
		service.activeAccount(token);
		return ResponseEntity.ok("felicitation votre accout a été activé avec success");
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/users")
	public List<AppUser> getAllUser(){
		return repo.findAll();
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/delete/{id}")
	public String deleteAppUser(@PathVariable Long id) {
		service.deleteUser(id);
		return String.format("le user qui prend l'id %s est supprimé avec succes", id);
	}
	    
}
