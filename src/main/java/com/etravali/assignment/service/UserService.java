package com.etravali.assignment.service;


import com.etravali.assignment.entity.User;

public interface UserService {

	public void saveUser(User user);

	public User getUser(int theId);

	public User getUserFromUserName(String emailId);
	
}
