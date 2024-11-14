package com.blog.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.app.model.User;
import com.blog.app.repo.UserRepo;
@Service
public class UserService {
	@Autowired
	UserRepo repo;

	
	public BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
	public User register(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return repo.save(user);
	}
	
	
}
