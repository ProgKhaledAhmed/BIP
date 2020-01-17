package com.khaled.BipBackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.khaled.BipBackend.dao.ProductDAO;
import com.khaled.BipBackend.dto.Product;

@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Product get(int productId) {
		try {
			return sessionFactory.getCurrentSession().get(Product.class, Integer.valueOf(productId));
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Product> list() {
		return sessionFactory.getCurrentSession().createQuery("FROM Product", Product.class).getResultList();
	}

	@Override
	public boolean addProduct(Product product) {
		try {
			sessionFactory.getCurrentSession().persist(product);
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateProduct(Product product) {
		try {
			sessionFactory.getCurrentSession().update(product);
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteProduct(Product product) {
		try {
			product.setActive(false);
			return this.updateProduct(product);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Product> listActiveProducts() {
		// Product is the name of entity mapped to PRODUCT table and active the
		// attribute of this entity
		String selectActiveProducts = "FROM Product WHERE active = :active";
		return sessionFactory.getCurrentSession().createQuery(selectActiveProducts, Product.class)
				.setParameter("active", true).getResultList();
	}

	@Override
	public List<Product> listActiveProductsByCategory(int categoryId) {
		// Product is the name of entity mapped to PRODUCT table and active the
		// attribute of this entity
		String selectActiveProductsByCategory = "FROM Product WHERE active = :active AND categoryId = :categoryId";
		return sessionFactory.getCurrentSession().createQuery(selectActiveProductsByCategory, Product.class)
				.setParameter("active", true).setParameter("categoryId", categoryId).getResultList();
	}

	@Override
	public List<Product> getLatestActiveProducts(int count) {
		// attribute of this entity
		String selectLatestActiveProducts = "FROM Product WHERE active = :active ORDER BY id";
		return sessionFactory.getCurrentSession().createQuery(selectLatestActiveProducts, Product.class)
				.setParameter("active", true).setFirstResult(0).setMaxResults(count).getResultList();
	}

}
