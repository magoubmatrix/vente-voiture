package com.app.email;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.entity.AppUser;
import com.app.entity.VerificationTokenEmail;
import com.app.exception.MailNotFoundException;
import com.app.mail.NotificationMail;
import com.app.repository.UserRepository;
import com.app.repository.VerificationTokenEmailRepository;
import com.app.services.MailService;



@Service
public class MailServiceImpl implements MailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private MailContentBuilder builder;
	
	public void getMail( NotificationMail mail) {
		MimeMessagePreparator mimeMessagePreparator = mimeMessage ->{
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
			helper.setTo(mail.getRecipt());
			helper.setSubject(mail.getSubject());
			helper.setText(builder.build(mail.getBody()));
			
		};
		try {
		javaMailSender.send(mimeMessagePreparator);}
		catch(MailNotFoundException ex) {
			throw new MailNotFoundException("le mail inserer est incorect :" + mail.getRecipt());
		}
	}
	
 
	
	/*
	private String vereficationTokenUser(AppUser user) {
	  String token = UUID.randomUUID().toString();
	  VerificationTokenEmail verif = new VerificationTokenEmail();
	  verif.setToken(token);
	  verif.setUser(user);
	  mailRepo.save(verif);
	  return token;
  }	
	
 */
	
}
