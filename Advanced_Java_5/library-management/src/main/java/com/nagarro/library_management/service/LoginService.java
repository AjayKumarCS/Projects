package com.nagarro.library_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nagarro.library_management.dao.UserDAO;

@Service
public class LoginService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Transactional
	public boolean check(String username, String password) {
		if(userDAO.check(username, password)) {
			return true;
		}
		return false;
	}
}
