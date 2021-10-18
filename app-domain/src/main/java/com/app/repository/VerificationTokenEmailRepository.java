package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.VerificationTokenEmail;

public interface VerificationTokenEmailRepository extends JpaRepository<VerificationTokenEmail, Long> {

	public VerificationTokenEmail findByToken(String token);
}
