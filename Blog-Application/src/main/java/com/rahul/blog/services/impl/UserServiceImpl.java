package com.rahul.blog.services.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rahul.blog.entities.User;
import com.rahul.blog.exceptions.ResourceNotFoundExceptions;
import com.rahul.blog.payloads.UserDto;
import com.rahul.blog.repositories.UserRepo;
import com.rahul.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Long userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundExceptions("User","Id", userId));
		user.setUserName(userDto.getUserName());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setAddress(userDto.getAddress());
		user.setCity(userDto.getCity());
		user.setState(userDto.getState());
		user.setCountry(userDto.getCountry());
		user.setEmail(userDto.getEmail());
		user.setMobile(userDto.getMobile());
		user.setPassword(userDto.getPassword());
		user.setRegistrationDate(userDto.getRegistrationDate());
		user.setStatus(userDto.getStatus());
		
		User updateUser = this.userRepo.save(user);
		return this.userToDto(updateUser);
	}

	@Override
	public UserDto getUserById(Long userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundExceptions("User","Id", userId));
		return this.modelMapper.map(user, UserDto.class);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> userList = this.userRepo.findAll();
		List<UserDto> userDtoList = userList.stream()
				.map(user -> this.userToDto(user)).collect(Collectors.toList());
		return userDtoList;
	}

	@Override
	public void deleteUser(Long userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundExceptions("User","Id", userId));
		this.userRepo.delete(user);
	}

	private User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
//		User user = new User();
//		user.setId(userDto.getId());
//		user.setUserName(userDto.getUserName());
//		user.setFirstName(userDto.getFirstName());
//		user.setLastName(userDto.getLastName());
//		user.setAddress(userDto.getAddress());
//		user.setCity(userDto.getCity());
//		user.setState(userDto.getState());
//		user.setCountry(userDto.getCountry());
//		user.setEmail(userDto.getEmail());
//		user.setMobile(userDto.getMobile());
//		user.setPassword(userDto.getPassword());
//		user.setRegistrationDate(userDto.getRegistrationDate());
//		user.setStatus(userDto.getStatus());
		return user;
	}
	
	private UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
//		UserDto userDto = new UserDto();
//		userDto.setId(user.getId());
//		userDto.setUserName(user.getUserName());
//		userDto.setFirstName(user.getFirstName());
//		userDto.setLastName(user.getLastName());
//		userDto.setAddress(user.getAddress());
//		userDto.setState(user.getState());
//		userDto.setCity(user.getCity());
//		userDto.setCountry(user.getCountry());
//		userDto.setMobile(user.getMobile());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setRegistrationDate(user.getRegistrationDate());
//		userDto.setStatus(user.getStatus());
		return userDto;
	}
}
