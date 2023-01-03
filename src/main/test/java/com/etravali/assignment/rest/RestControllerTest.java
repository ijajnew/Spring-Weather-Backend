package com.etravali.assignment.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.etravali.assignemnt.config.AppConfig;
import com.etravali.assignment.entity.User;


@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class RestControllerTest {
	
	@Autowired
	private WeatherRestController weatherRestController;
	
	@Autowired
	private UserRestController UserRestController;
	
	//test for weather update
	@Test
	public void testPositiveTemp() {
		
		WeatherResponse response = weatherRestController.getWeather("pune", "email");
		
		assertNotNull(response.getTemp_c());
		assertNotNull(response.getCity());
		assertNotNull(response.getMessage());
	}
	
	//test for get user
	@Test
	public void testGetUser() {
		
		User user = UserRestController.getUser(1);
		
		assertNotNull(user.getId());
		assertNotNull(user.getFirstName());
	}
	
	//test for Authentication user
	@Test
	public void testAuthUser() {
		
		User user = UserRestController.getUserAuthentication("test");
		
		assertNotNull(user.getId());
		assertNotNull(user.getFirstName());
	}
	
	//test for add user
	@Test
	public void testAddUser() {
		
		User user = new User();
		user.setFirstName("Test");
		user.setLastName("LastName");
		user.setCity("City");
		user.setNotificationType("email");
		user.setUserName("test");
		
		
		User result = UserRestController.addUser(user);
		
		assertEquals(result.getFirstName(), "Test");
	}
	
	//test for update user
	@Test
	public void testUpdateUser() {
		
		User user = new User();
		user.setId(1);
		user.setFirstName("Test");
		user.setLastName("LastName");
		user.setCity("City");
		user.setNotificationType("email");
		user.setUserName("test");
		
		
		UserRestController.addUser(user);
		
		User result = UserRestController.updateUser(user);
		
		assertEquals(result.getFirstName(), "Test");
	}
}
