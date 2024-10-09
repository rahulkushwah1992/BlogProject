package com.rahul.blog.services;

import java.util.List;

import com.rahul.blog.payloads.UserDto;

public interface UserService {
	
	//create
	UserDto createUser(UserDto user);
	//update
	UserDto updateUser(UserDto user, Long userId);
	//getId
	UserDto getUserById(Long userId);
	//getAll
	List<UserDto> getAllUser();
	//delete
	void deleteUser(Long userId);

}
