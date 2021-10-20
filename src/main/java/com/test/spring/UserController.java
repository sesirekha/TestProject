package com.test.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.spring.model.User;
import com.test.spring.service.UserService;


@RestController
public class UserController {
	
    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<String> signUpUser(@RequestBody User user){
    	if(userService.createUser(user) != null) {
    		return new ResponseEntity<String>("User created", HttpStatus.CREATED);
    	}
    	else {
    		return new ResponseEntity<String>("User already exists", HttpStatus.CONFLICT);
    	}
    	
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> loginUser(@RequestBody User user){
    	if(userService.loginUser(user) != null) {
    		return new ResponseEntity<String>("User logged in successfully", HttpStatus.CREATED);
    	}
    	else {
    		return new ResponseEntity<String>("User not exists", HttpStatus.CONFLICT);
    	}
    }
    
    @RequestMapping(value = "/allusers", method = RequestMethod.GET)
    public List<User> getAllUsers(){
    	return userService.getAllUsers();
    }
}
