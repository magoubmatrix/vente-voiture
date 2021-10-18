package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entity.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Long> {

	public Optional<AppUser> findByUsername(String username);
	
	public Optional<AppUser> findByEmail(String email);
	
	//@Query("select case count(s)>0 then true else false end from AppUser s where s.email = ?1")
	//public boolean checkIfEmailExist(String email);
	
	@Query("select s.username from AppUser s")
	public List<String> getAllUsername();
	
	
}
