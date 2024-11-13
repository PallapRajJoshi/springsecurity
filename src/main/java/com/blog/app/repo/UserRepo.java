package com.blog.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.app.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
