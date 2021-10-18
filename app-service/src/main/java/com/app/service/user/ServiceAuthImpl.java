 package com.app.service.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import com.app.entity.VerificationTokenEmail;
import com.app.mail.NotificationMail;
import com.app.repository.UserRepository;
import com.app.repository.VerificationTokenEmailRepository;
import com.app.security.jwt.JwtProvider;
import com.app.services.MailService;
import com.app.services.ServiceAuth;
import com.app.user.UserSignUp;
import com.app.user.UserVisite;

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
	@Autowired
	private MailService mailService;
	@Autowired
	private VerificationTokenEmailRepository tokenEmailRepo;
	
	@Override
	@Transactional
	public String signup(UserSignUp user) {
		AppUser appUser = new AppUser();
		List<String> names = repo.getAllUsername();
		names.forEach(System.out::println);
		String username = user.getUsername();
		
		if(names.contains(username))
			throw new RuntimeException("cet username existe deja choisir un autre");
		
 		appUser.setUsername(user.getUsername());
		appUser.setEmail(user.getEmail());
		String password = passwordEncoder.encode(user.getPassword());
		appUser.setPassword(password);
		appUser.setRole(user.getRole());		//appUser.setActive(true);
		if(user.getRole() == null)
		appUser.setRole(AppRole.ROLE_USER);
		repo.save(appUser);
		String tokenEmail = giveTokenToUser(appUser);
		mailService.getMail(new NotificationMail("felicitation you are signup" , user.getEmail(),"visite this url to validate your account please : "
				+ "http://localhost:8081/auth/verificationAccount/"+tokenEmail));
		return "hello monsieur"+user.getUsername();
	}
	
	@Override
	public void activeAccount(String token) {
		VerificationTokenEmail tokenEmail = tokenEmailRepo.findByToken(token);
		AppUser user = tokenEmail.getUser();
		user.setActive(true);
		repo.save(user);
	}
	

	@Override
	public AuthenticationResponse login(UserVisite user) {
		  
		   String username = user.getUsername();
		   AppUser appUser = repo.findByUsername(username)
		    		  .orElseThrow(()-> new UsernameNotFoundException("this username is invalid"));
		   if(appUser.isActive()) {
		   Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, user.getPassword()));
	      SecurityContextHolder.getContext().setAuthentication(authentication);
	      String name = SecurityContextHolder.getContext().getAuthentication().getName();
	      List<GrantedAuthority> authority = List.of(new SimpleGrantedAuthority(appUser.getRole().name()));
	      Map<String, Object> authorities = new HashMap<String,Object>();
	      authority.forEach(auth -> authorities.put("authority", auth.getAuthority()));
	      
	      String token = jwtProvider.createToken(authorities, username);
		   
		   return new AuthenticationResponse(token,name);}
		   else {
			   return new AuthenticationResponse("s'il vous plais verifié votre email pour activer votre compte et merci" , username);
		   }
	}
	
	
	@Override
	public void deleteUser(Long id) {
		AppUser user = repo.findById(id).orElseThrow(()-> new UsernameNotFoundException("this id est invalide"));
		repo.delete(user);
	}
	
	private String giveTokenToUser(AppUser user) {
		VerificationTokenEmail tokenEmail = new VerificationTokenEmail();
		String token = UUID.randomUUID().toString();
		tokenEmail.setToken(token);
		tokenEmail.setUser(user);
		tokenEmailRepo.save(tokenEmail);	
		return token;
	}

}
