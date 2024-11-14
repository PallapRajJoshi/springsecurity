package com.blog.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.app.model.User;
import com.blog.app.model.UserPrincipal;
import com.blog.app.repo.UserRepo;

@Service
public class MyUserDetailService implements UserDetailsService {
	@Autowired
	private UserRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		User user=repo.findByUsername(username);
		
		if(user==null) {
			System.out.println("User Not Found");
			throw new UsernameNotFoundException("User not found");
		}
		return new UserPrincipal(user);
	}
	
	
	
	
}
