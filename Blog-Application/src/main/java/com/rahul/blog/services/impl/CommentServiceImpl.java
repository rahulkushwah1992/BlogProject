package com.rahul.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rahul.blog.entities.Comment;
import com.rahul.blog.entities.Post;
import com.rahul.blog.entities.User;
import com.rahul.blog.exceptions.ResourceNotFoundExceptions;
import com.rahul.blog.payloads.CommentDto;
import com.rahul.blog.repositories.CommentRepo;
import com.rahul.blog.repositories.PostRepo;
import com.rahul.blog.repositories.UserRepo;
import com.rahul.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Long postId, Long userId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundExceptions("postId", "id", postId));
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundExceptions("userId", "id", userId));
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		comment.setUser(user);
		Comment addComment = this.commentRepo.save(comment);
		return this.modelMapper.map(addComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Long commentId) {
		Comment comment = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundExceptions("commentId", "id", commentId));
		this.commentRepo.delete(comment);
	}

}
