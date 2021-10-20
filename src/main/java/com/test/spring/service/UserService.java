package com.test.spring.service;

import java.util.List;

import com.test.spring.model.User;


public interface UserService {

	User createUser(User user);
	User loginUser(User user);
	List<User> getAllUsers();
	
}
