package com.nagarro.library_management.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nagarro.library_management.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean check(String username, String password) {
		try {
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session.get(User.class, username);
		if(user.getPassword().equals(password)) {
			return true;
		}
		return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
