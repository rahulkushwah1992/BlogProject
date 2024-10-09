package com.rahul.blog.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rahul.blog.entities.User;
import java.util.List;


public interface UserRepo extends JpaRepository<User, Long>{

	Optional<User> findByEmail(String email);
}
