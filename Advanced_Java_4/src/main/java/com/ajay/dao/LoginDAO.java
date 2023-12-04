package com.ajay.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ajay.entity.Login;

@Repository
public class LoginDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean check(String userName, String userPass) {
		Session session = sessionFactory.getCurrentSession();
		Login login = (Login) session.get(Login.class, userName);
		if(login.getUserPass().equals(userPass)) {
			return true;
		}
		return false;
	}
}
