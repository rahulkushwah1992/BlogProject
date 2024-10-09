package com.rahul.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.rahul.blog.entities.Category;
import com.rahul.blog.entities.Post;
import com.rahul.blog.entities.User;
import com.rahul.blog.exceptions.ResourceNotFoundExceptions;
import com.rahul.blog.payloads.PostDto;
import com.rahul.blog.payloads.PostResponse;
import com.rahul.blog.repositories.CategoryRepo;
import com.rahul.blog.repositories.PostRepo;
import com.rahul.blog.repositories.UserRepo;
import com.rahul.blog.services.PostService;


@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMap;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto, Long userId, Long categoryId) {
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundExceptions("userId", "user id", userId));
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundExceptions("categoryId", "category id", categoryId));
		Post post = this.modelMap.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setCreationDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post savePost = this.postRepo.save(post);
		return this.modelMap.map(savePost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Long postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundExceptions("postId", "post Id", postId));
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		post.setTitle(postDto.getTitle());
		Post updatePost = this.postRepo.save(post);
		return this.modelMap.map(updatePost, PostDto.class);
	}

	@Override
	public void deletePost(Long postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundExceptions("postId", "post Id", postId));
		this.postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String shortBy, String shortDir) {
		Sort sorts = (shortDir.equalsIgnoreCase("asc")) ? Sort.by(shortBy).ascending() : Sort.by(shortBy).descending();
		Pageable p = PageRequest.of(pageNumber, pageSize, sorts);
		Page<Post> pagePost = this.postRepo.findAll(p);
		List<Post> postList = pagePost.getContent();
		List<PostDto> dtoList = postList.stream().map((post) -> this.modelMap.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(dtoList);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElement(pagePost.getTotalElements());
		postResponse.setTotalPage(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		return postResponse;
	}

	@Override
	public PostDto getPostById(Long postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundExceptions("postId", "post Id", postId));
		return this.modelMap.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(Long caregoryId) {
		Category cat = this.categoryRepo.findById(caregoryId)
		.orElseThrow(() -> new ResourceNotFoundExceptions("categoryId","category Id",caregoryId));
		List<Post> postList = this.postRepo.findByCategory(cat);
		
		List<PostDto> postDtoList = postList.stream()
				.map((post) -> this.modelMap.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtoList;
	}

	@Override
	public List<PostDto> getPostByUser(Long userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundExceptions("userId", "user Id", userId));
		List<Post> postList = this.postRepo.findByUser(user);
		
		List<PostDto> postDtoList = postList.stream()
				.map((post) -> this.modelMap.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtoList;
	}

}
