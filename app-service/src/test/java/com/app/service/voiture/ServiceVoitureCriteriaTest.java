package com.app.service.voiture;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.app.entity.AppUser;
import com.app.repository.UserRepository;

@DataJpaTest
public class ServiceVoitureCriteriaTest {
  
	@Autowired
	private UserRepository repo ;
	
	@Test
	public void testSaveUser() {
		AppUser user = new AppUser();
		user.setUsername("junit");
		user.setPassword("junit");
		user.setEmail("junit@junit");
		repo.save(user);
	    
	}
}
