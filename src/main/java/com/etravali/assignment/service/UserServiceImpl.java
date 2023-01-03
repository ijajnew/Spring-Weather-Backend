package com.etravali.assignment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.etravali.assignment.dao.UserDAO;
import com.etravali.assignment.entity.User;

@Service
public class UserServiceImpl implements UserService {

	// need to inject customer dao
	@Autowired
	private UserDAO userDAO;

	@Override
	@Transactional
	public void saveUser(User user) {
		userDAO.saveUser(user);
	}

	@Override
	@Transactional
	public User getUser(int theId) {
		return userDAO.getUser(theId);
	}

	@Override
	@Transactional
	public User getUserFromUserName(String userName) {
		
		return userDAO.getUserFromUserName(userName);
	}
}





