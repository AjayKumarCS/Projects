package com.ajay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ajay.dao.LoginDAO;

@Service
public class LoginService {
	
	@Autowired
	private LoginDAO loginDAO;
	
	@Transactional
	public boolean check(String userName, String userPass) {
		if(loginDAO.check(userName, userPass)) {
			return true;
		}
		return false;
	}
}
