package com.etravali.assignment.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.etravali.assignment.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveUser(User theUser) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save/upate the user
		currentSession.saveOrUpdate(theUser);
		
	}

	@Override
	public User getUser(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// now read from database using the primary key
		User theUser = currentSession.get(User.class, theId);
		
		return theUser;
	}

	@Override
	public User getUserFromUserName(String userName) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		// create a query
		Query<User> theQuery = 
				currentSession.createQuery("from User where user_name=:userName", User.class);
		theQuery.setParameter("userName", userName);
		
		// execute query and get result
		User user = theQuery.getResultList().get(0);
		// return the results		
		return user;
	}

}











