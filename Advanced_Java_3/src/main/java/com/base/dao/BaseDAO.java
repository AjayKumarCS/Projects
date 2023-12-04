package com.base.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BaseDAO {

	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
	
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
}
