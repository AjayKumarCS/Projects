package com.ajay.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ajay.entity.Files;

@Repository
public class FilesDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<String> getFiles() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT name FROM Files";
		List<String> files;
		files = session.createQuery(hql).list();
		return files;
	}
	
	public void setFiles(String file) {
		Session session = sessionFactory.getCurrentSession();
		session.save(new Files(file));
	}
}
