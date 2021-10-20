package com.test.spring.dao;

import java.util.List;

import com.test.spring.model.User;

public interface UserDAO {

	User createUser(User user);
	User loginUser(User user);
	List<User> getAllUsers();
}
