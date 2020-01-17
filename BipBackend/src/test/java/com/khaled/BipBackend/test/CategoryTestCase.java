package com.khaled.BipBackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.khaled.BipBackend.dao.CategoryDAO;
import com.khaled.BipBackend.dto.Category;

public class CategoryTestCase {

	private static AnnotationConfigApplicationContext context;

	private static CategoryDAO categoryDAO;

	private Category category;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.khaled.BipBackend");
		context.refresh();

		categoryDAO = (CategoryDAO) context.getBean("categoryDAO");
	}

	@Test
	public void testAddCategory() {
		category = new Category();
		
		category.setName("Mobile2");
		category.setDescription("Mobile Desc2");
		category.setImageURL("CAT_22.png");
		
		assertEquals("Successfully added new Category", true, categoryDAO.addCategory(category));
	}
	
	@Test
	public void testGetCategory() {
		category = categoryDAO.get(3);
		assertEquals("Successfully Fetched!", "Labtop", category.getName());
	}
	
	@Test
	public void testUpdateCategory() {
		category = categoryDAO.get(3);
		category.setName("LABTOPS");
		assertEquals("Successfully Updated!", true, categoryDAO.updateCategory(category));
	}
	
	//Soft Delete
	@Test
	public void testDeleteCategory() {
		category = categoryDAO.get(3);
		assertEquals("Successfully Deleted!", true, categoryDAO.deleteCategory(category));
	}

	@Test
	public void testListCategory() {
		assertEquals("Successfully Retrieved!", -1, categoryDAO.list().size());
	}

	@Test
	public void testCategoryCRUDOperations() {
		category = new Category();
		
		// Adding new Category
		category.setName("Clothes");
		category.setDescription("Clothes Desc");
		category.setImageURL("CAT_10.png");
		
		assertEquals("Successfully added new Category", true, categoryDAO.addCategory(category));
		
		// Updating the new Category
		category = categoryDAO.get(7);
		category.setName("Winter Clothes");
		assertEquals("Successfully Updated!", true, categoryDAO.updateCategory(category));
		
		// Deleting the new Category
		assertEquals("Successfully Deleted!", true, categoryDAO.deleteCategory(category));
		
		// Fetching all Categories
		assertEquals("Successfully Retrieved!", -1, categoryDAO.list().size());
	}
	
}
