package com.rahul.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.rahul.blog.entities.Comment;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private Long id;
	@NotEmpty
	@Size(min = 4, message = "User name must be min of 4 characters")
	private String userName;
	@NotEmpty
	private String firstName;
	private String lastName;
	@NotEmpty
	private String address;
	@NotEmpty
	private String state;
	@NotEmpty
	private String city;
	@NotEmpty
	@Size(min = 2, max = 2, message = "country code max of 2 characters")
	private String country;
	@NotEmpty
	@Email(message = "Email address is not valid !!")
	private String email;
	@Pattern(regexp = "^[0-9]{10}$", message = "Mobile number max 10-digit number")
	private String mobile;
	@NotEmpty
	@Size(min = 3, max = 10, message = "Password must be min of 3 chars and max of 10 chars")
	private String password;
	
	private Date registrationDate;
	private Boolean status;
	
	private Set<CommentDto> comments = new HashSet<>();
}
