package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Long> {

	public Optional<AppUser> findByUsername(String username);
}
