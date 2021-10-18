package com.app.email;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;


@Configuration
public class EmailConf {

	 @Bean
	    public JavaMailSender javaMailSender() { 
		  
		JavaMailSenderImpl javaMailSender =  new JavaMailSenderImpl();
		javaMailSender.setUsername("42628f5bd5494f");
		javaMailSender.setPassword("ed245cdbb15621");
		javaMailSender.setHost("smtp.mailtrap.io");
		javaMailSender.setPort(25);
		return javaMailSender;
	    }

	
	
}