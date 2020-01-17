package com.khaled.BipFrontend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.khaled.BipBackend.dao.CategoryDAO;
import com.khaled.BipBackend.dao.ProductDAO;
import com.khaled.BipBackend.dto.Category;
import com.khaled.BipBackend.dto.Product;
import com.khaled.BipFrontend.exception.ProductNotFoundException;

@Controller
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private ProductDAO productDAO;

	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("page");
		modelAndView.addObject("title", "Home");

		logger.info("Inside the PageController - index Method - INFO");
		logger.debug("Inside the PageController - index Method - DEBUG");
		// Passing the category list
		modelAndView.addObject("categories", categoryDAO.list());

		modelAndView.addObject("userClickHome", true);
		return modelAndView;
	}

	@RequestMapping(value = "/about")
	public ModelAndView about() {
		ModelAndView modelAndView = new ModelAndView("page");
		modelAndView.addObject("title", "About Us");
		modelAndView.addObject("userClickAbout", true);
		return modelAndView;
	}

	@RequestMapping(value = "/products")
	public ModelAndView products() {
		ModelAndView modelAndView = new ModelAndView("page");
		modelAndView.addObject("title", "Products");
		modelAndView.addObject("userClickProducts", true);
		return modelAndView;
	}

	@RequestMapping(value = "/contact")
	public ModelAndView contact() {
		ModelAndView modelAndView = new ModelAndView("page");
		modelAndView.addObject("title", "Contact Us");
		modelAndView.addObject("userClickContact", true);
		return modelAndView;
	}

	@RequestMapping(value = "/show/all/products")
	public ModelAndView showAllProducts() {
		ModelAndView modelAndView = new ModelAndView("page");
		modelAndView.addObject("title", "All Products");

		// Passing the category list
		modelAndView.addObject("categories", categoryDAO.list());

		modelAndView.addObject("userClickAllProducts", true);
		return modelAndView;
	}

	@RequestMapping(value = "/show/category/{id}/products")
	public ModelAndView showCategoryProducts(@PathVariable("id") int id) {
		ModelAndView modelAndView = new ModelAndView("page");

		// categoryDAO to fetch a single category.
		Category category = categoryDAO.get(id);
		modelAndView.addObject("title", category.getName());

		// Passing the category list
		modelAndView.addObject("categories", categoryDAO.list());

		// Passing the retrieved category with passed id
		modelAndView.addObject("category", category);

		modelAndView.addObject("userClickCategoryProducts", true);
		return modelAndView;
	}

	@RequestMapping(value = "/show/{id}/product")
	public ModelAndView showSingleProduct(@PathVariable("id") int id) throws ProductNotFoundException{

		ModelAndView modelAndView = new ModelAndView("page");

		Product product = productDAO.get(id);
		
		if(product == null) throw new ProductNotFoundException();
		
		product.setViews(product.getViews() + 1);
		productDAO.updateProduct(product);

		modelAndView.addObject("title", product.getName());
		modelAndView.addObject("product", product);
		modelAndView.addObject("userClickShowProduct", true);

		return modelAndView;
	}
}
