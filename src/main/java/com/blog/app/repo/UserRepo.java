package com.blog.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.app.model.User;
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
User findByUsername(String username);
}
