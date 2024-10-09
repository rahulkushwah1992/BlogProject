package com.rahul.blog.services;

import java.util.List;

import com.rahul.blog.entities.Post;
import com.rahul.blog.payloads.PostDto;
import com.rahul.blog.payloads.PostResponse;

public interface PostService {

	//create
	PostDto createPost(PostDto postDto, Long userId, Long categoryId);
	
	//update
	PostDto updatePost(PostDto postDto, Long postId);
	
	//delete
	void deletePost(Long postId);
	
	//get all post
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String shortBy, String shortDir);
	
	//get post by id
	PostDto getPostById(Long postId);
	
	//get All post by category
	List<PostDto> getPostByCategory(Long caregoryId);
	
	//get All post by user
	List<PostDto> getPostByUser(Long userId);
}
