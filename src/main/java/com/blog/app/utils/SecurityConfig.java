package com.blog.app.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.blog.app.services.MyUserDetailService;
import com.blog.app.utils.JwtFilter.JwtFilter;

import jakarta.annotation.security.PermitAll;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private MyUserDetailService userDetailService;
	
	@Autowired
	private JwtFilter jwtFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		return	http.csrf(customizer->customizer.disable()).
		authorizeHttpRequests(request->request
//				this skip authentication for login and register
				.requestMatchers("/register","/logins")
				.permitAll()
				.anyRequest()
				.authenticated()).
//		formLogin().disable().
		formLogin(Customizer.withDefaults()).
		httpBasic(Customizer.withDefaults()).
		sessionManagement(session->
		session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
		
		addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class ).
		build();
//		for Access denied
//		http.authorizeHttpRequests(request->request.anyRequest().authenticated());
//		http.formLogin(Customizer.withDefaults());
//		for postman
//		http.httpBasic(Customizer.withDefaults());
//		disable form login and give login form again and again
//		http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
//		return http.build();
		
		
	
		
		
		
	}
	
//	default role providing 
//	@Bean
//	public UserDetailsService userDetailsService(){
//		UserDetails user1=User
//				.withDefaultPasswordEncoder()
//		.username("pal")
//		.password("pal")
//		.roles("USER")
//		.build();
//		
//		
//		UserDetails user2=User
//				.withDefaultPasswordEncoder()
//		.username("pall")
//		.password("pall")
//		.roles("ADMIN")
//		.build();
//		
//		return new InMemoryUserDetailsManager(user1,user2);
//	}

	
//Using Database authentication
	@Bean
	public AuthenticationProvider authenticationProvider() {
		// Dao is authentication provider
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		provider.setUserDetailsService(userDetailService);
		return provider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	return config.getAuthenticationManager();	
	}
	
}
