package com.test.spring.service;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.spring.dao.UserDAO;
import com.test.spring.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDao;
	
	@Override
	public User createUser(User user) {
		Base64.Encoder encoder = Base64.getEncoder();
    	user.setPassword(encoder.encodeToString(user.getPassword().getBytes()));
		return userDao.createUser(user);
	}
	
	@Override
	public User loginUser(User user) {
		Base64.Encoder encoder = Base64.getEncoder();
    	user.setPassword(encoder.encodeToString(user.getPassword().getBytes()));
		return userDao.loginUser(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

}
