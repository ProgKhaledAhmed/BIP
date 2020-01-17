package com.khaled.BipBackend.dao;

import java.util.List;

import com.khaled.BipBackend.dto.Product;

public interface ProductDAO {

	public Product get(int productId);

	public List<Product> list();

	public boolean addProduct(Product product);

	public boolean updateProduct(Product product);

	public boolean deleteProduct(Product product);

	public List<Product> listActiveProducts();

	public List<Product> listActiveProductsByCategory(int categoryId);

	public List<Product> getLatestActiveProducts(int count);
}
