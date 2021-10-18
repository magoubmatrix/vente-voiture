package com.app.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;


@Entity

public class VerificationTokenEmail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotNull
	private String token;
	@OneToOne
	private AppUser user;
	
	
	
	public VerificationTokenEmail() {
		super();
	}



	public VerificationTokenEmail(@NotNull String token, AppUser user) {
		super();
		this.token = token;
		this.user = user;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getToken() {
		return token;
	}



	public void setToken(String token) {
		this.token = token;
	}



	public AppUser getUser() {
		return user;
	}



	public void setUser(AppUser user) {
		this.user = user;
	}
	
	
	
}
