package com.ajay.dao;

import java.util.List;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ajay.entity.Product;

@Repository
public class ProductDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void addProduct(Product product) {
		Session currentSession = sessionFactory.openSession();
        currentSession.save(product);
	}
	
	@SuppressWarnings("unchecked")
	public List<String> availableColors() {
		List<String> colors;
		String hql = "SELECT distinct(color) FROM Product";
		Session session = sessionFactory.openSession();
		colors = session.createQuery(hql).list();
		return colors;
	}
	
	public List<List<String>> search(String color, String size, String gender, String sortBy){
		Session currentSession = sessionFactory.getCurrentSession();
		String hql = "FROM Product WHERE color='" + color + "' AND size='" + size + "' AND gender='" + gender
				+ "'" + sortBy;
		@SuppressWarnings("unchecked")
		List<Product> products = currentSession.createQuery(hql).list();
		List<List<String>> result = new ArrayList<>();
		for(Product p: products) {
			result.add(p.getDetails());
		}
		return result;
	}
}
