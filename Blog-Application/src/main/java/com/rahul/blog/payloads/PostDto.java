package com.rahul.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.rahul.blog.entities.Comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	
	private Long id;
	
	private String title;
	
	private String content;
	
	private String imageName;
	
	private Date creationDate;
	
	private UserDto user;
	
	private CategoryDto category;
	
	private Set<CommentDto> comments = new HashSet<>();

}
