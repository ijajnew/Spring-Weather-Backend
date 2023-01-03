package com.etravali.assignment.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etravali.assignment.entity.User;
import com.etravali.assignment.service.UserService;

@RestController
@RequestMapping("/api")
public class UserRestController {
	
	@Autowired
	public UserService userService;
	
	@GetMapping("/users/{userId}")
	public User getUser(@PathVariable int userId){
		User user = userService.getUser(userId);
		
		if(user == null) {
			throw new RuntimeException("User not found!!");
		}
		return user;
	}
	
	@GetMapping("/users/auth/{userName}")
	public User getUserAuthentication(@PathVariable String userName){
		
		User user = userService.getUserFromUserName(userName);
		
		if(user == null) 
		{ 
			throw new RuntimeException("User not found!!"); 
		}
		return user;

	}
	
	@PostMapping("/users")
	public User addUser(@RequestBody User user) {
		
		userService.saveUser(user);
		return user;
	}
	
	@PutMapping("/users")
	public User updateUser(@RequestBody User user) {
		
		userService.saveUser(user);
		return user;
	}
	
}
