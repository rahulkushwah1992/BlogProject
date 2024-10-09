package com.rahul.blog.services;

import com.rahul.blog.payloads.CommentDto;

public interface CommentService {
	
	CommentDto createComment(CommentDto commentDto, Long postId, Long userId);
	
	void deleteComment(Long commentId);

}
