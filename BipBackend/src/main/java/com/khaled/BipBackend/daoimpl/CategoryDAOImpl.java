package com.khaled.BipBackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.khaled.BipBackend.dao.CategoryDAO;
import com.khaled.BipBackend.dto.Category;

@Repository("categoryDAO")
@Transactional
public class CategoryDAOImpl implements CategoryDAO {

	private static List<Category> categories = new ArrayList<>();

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> list() {

		//HQL Hibernate Query Language
		String selectActiveCategory = "FROM Category WHERE active = :active";

		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory);

		query.setParameter("active", true);

		return query.getResultList();
	}

	@Override
	public Category get(int id) {
		return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
	}

	@Override
	@Transactional
	public boolean addCategory(Category category) {
		try {
			sessionFactory.getCurrentSession().persist(category);
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateCategory(Category category) {
		try {
			sessionFactory.getCurrentSession().update(category);
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteCategory(Category category) {
		category.setActive(false);
		try {
			sessionFactory.getCurrentSession().update(category);
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}
}
