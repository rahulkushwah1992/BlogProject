package com.rahul.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rahul.blog.payloads.ApiResponse;
import com.rahul.blog.payloads.CommentDto;
import com.rahul.blog.services.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/post/{postId}/user/{userId}/comment")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commnetDto, 
			@PathVariable Long postId, @PathVariable Long userId){
		CommentDto saveCommnetDto = this.commentService.createComment(commnetDto, postId, userId);
				return new ResponseEntity<CommentDto>(saveCommnetDto, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/comment/{commnetId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Long commnetId){
		this.commentService.deleteComment(commnetId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("comment delete successfully!!",true),HttpStatus.OK);
	}

}
