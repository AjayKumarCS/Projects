package com.pms.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.base.dao.BaseDAO;
import com.pms.model.Product;

public class ProductDAO {

	public void insertProduct(Product product) {
		try {
			EntityManager manager = BaseDAO.getEntityManager();
			Query query = manager.createQuery("SELECT sum(imgSize) FROM Product");
			Object size = null;
			size = query.getResultList().get(0);
			if (!(size == null)) {
				if (!((long) size == 0)) {
					long totalSize = (long) size + product.getImgSize();
					if (totalSize > 10485760) {
						throw new Exception("total file size exceeds 10mb");
					}
				}
			}
			manager.getTransaction().begin();
			manager.persist(product);
			manager.getTransaction().commit();
			manager.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateProduct(int id, String title, String quantity, String size) {
		try {
			EntityManager manager = BaseDAO.getEntityManager();
			manager.getTransaction().begin();
			Product p = manager.find(Product.class, id);
			p.setTitle(title);
			p.setQuantity(quantity);
			p.setSize(size);
			manager.persist(p);
			manager.getTransaction().commit();
			manager.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateProduct(int id, String title, String quantity, String size, String img, long imgSize) {
		try {
			EntityManager manager = BaseDAO.getEntityManager();
			manager.getTransaction().begin();
			Product p = manager.find(Product.class, id);
			p.setTitle(title);
			p.setQuantity(quantity);
			p.setSize(size);
			p.setImg(img);
			p.setImgSize(imgSize);
			manager.persist(p);
			manager.getTransaction().commit();
			manager.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteProduct(int id) {
		try {
			EntityManager manager = BaseDAO.getEntityManager();
			manager.getTransaction().begin();
			Product p = manager.find(Product.class, id);
			manager.remove(p);
			manager.getTransaction().commit();
			manager.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Product selectProduct(int id) {
			EntityManager manager = BaseDAO.getEntityManager();
			manager.getTransaction().begin();
			Product p = manager.find(Product.class, id);
			manager.getTransaction().commit();
			manager.close();
			return p;
	}

	@SuppressWarnings("unchecked")
	public List<Product> selectAllProduct() {
		List<Product> products = new ArrayList<>();
		EntityManager manager = BaseDAO.getEntityManager();
		products = manager.createQuery("SELECT p FROM Product p").getResultList();
		manager.close();
		return products;
	}

}
