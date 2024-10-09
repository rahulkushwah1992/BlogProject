package com.rahul.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rahul.blog.entities.Category;
import com.rahul.blog.entities.Post;
import com.rahul.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Long>{
	
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);

}
