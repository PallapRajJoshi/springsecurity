package com.blog.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.app.model.User;
import com.blog.app.services.UserService;

@RestController
public class UserController {
@Autowired
private UserService service;

	@PostMapping("/register")
	public User register(@RequestBody User user) {
		System.out.println(user.getUsername()+" "+user.getPassword());
		return service.register(user);
		
	}
}
