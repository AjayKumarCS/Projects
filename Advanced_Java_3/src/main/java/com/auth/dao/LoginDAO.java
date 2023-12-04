package com.auth.dao;

import javax.persistence.EntityManager;

import com.auth.model.Login;
import com.base.dao.BaseDAO;

public class LoginDAO {

	public Boolean check(String usrName, String usrPass) {
		try {
			EntityManager em = BaseDAO.getEntityManager();

			Login user = em.find(Login.class, usrName);
			if (usrPass.equals(user.getUserPass())) {
				em.close();
				return true;
			}
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
