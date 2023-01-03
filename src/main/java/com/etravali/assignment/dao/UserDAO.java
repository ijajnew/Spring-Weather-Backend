package com.etravali.assignment.dao;


import com.etravali.assignment.entity.User;

public interface UserDAO {

	public void saveUser(User theUser);

	public User getUser(int theId);

	public User getUserFromUserName(String userName);
	
}
