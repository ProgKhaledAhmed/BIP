package com.khaled.BipBackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.khaled.BipBackend.dao.ProductDAO;
import com.khaled.BipBackend.dto.Product;

public class ProductTestCase {
	
	private static AnnotationConfigApplicationContext context;

	private static ProductDAO productDAO;

	private Product product;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.khaled.BipBackend");
		context.refresh();

		productDAO = (ProductDAO) context.getBean("productDAO");
	}

	@Test
	public void testProductCRUDOperations() {
		product = new Product();
		
		// Adding new product
		product.setName("Oppo Selfie S53");
		product.setBrand("Oppo");
		product.setDescription("Oppo Selfie S53 Desc");
		product.setUnitPrice(25000);
		product.setActive(true);
		product.setCategoryId(3);
		product.setSupplierId(3);
		
		assertEquals("Something went wrong while adding new product!", true, productDAO.addProduct(product));
		
		// Updating the new product
		product = productDAO.get(2);
		product.setName("Samsung Galaxy S7");
		assertEquals("Something went wrong while updating the product!", true, productDAO.updateProduct(product));
		
		// Deleting the new product
		assertEquals("Something went wrong while deleting the product!", true, productDAO.deleteProduct(product));
		
		// Fetching all Products
		assertEquals("Successfully Retrieved!", 6, productDAO.list().size());
	}
}
