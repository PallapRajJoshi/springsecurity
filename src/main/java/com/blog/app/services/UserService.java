package com.blog.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.app.model.User;
import com.blog.app.repo.UserRepo;
@Service
public class UserService {
	@Autowired
	UserRepo repo;
@Autowired
AuthenticationManager authManager;

@Autowired
private JWTService jwtservice;
	
	public BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
	public User register(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return repo.save(user);
	}
	
	
	public String verify(User user) {
		Authentication authentication=authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		
		if(authentication.isAuthenticated()) 
			return jwtservice.generateToken(user.getUsername());
		return "fail";
		
		
	}
	
}
