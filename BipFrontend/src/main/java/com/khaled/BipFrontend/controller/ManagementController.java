package com.khaled.BipFrontend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.khaled.BipBackend.dao.CategoryDAO;
import com.khaled.BipBackend.dao.ProductDAO;
import com.khaled.BipBackend.dto.Category;
import com.khaled.BipBackend.dto.Product;
import com.khaled.BipFrontend.utils.FileUploadUtility;
import com.khaled.BipFrontend.validator.ProductValidator;

@Controller
@RequestMapping("/manage")
public class ManagementController {

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private ProductDAO productDAO;

	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);

	// Go to manageProducts page
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name = "operation", required = false) String operation) {
		ModelAndView modelAndView = new ModelAndView("page");
		modelAndView.addObject("userClickManageProducts", true);
		modelAndView.addObject("title", "Manage Products");

		Product nProduct = new Product();
		nProduct.setSupplierId(1);
		nProduct.setActive(true);

		modelAndView.addObject("product", nProduct);

		if (operation != null) {
			if (operation.equals("product")) {
				modelAndView.addObject("message", "Product Submitted Successfully!");
			}  else if(operation.equals("categorys")) {
				modelAndView.addObject("message", "Category Submitted Successfully!");
			}
		}

		return modelAndView;
	}
	
	@RequestMapping(value = "/{id}/product", method = RequestMethod.GET)
	public ModelAndView showEditProducts(@PathVariable int id) {
		ModelAndView modelAndView = new ModelAndView("page");
		modelAndView.addObject("userClickManageProducts", true);
		modelAndView.addObject("title", "Manage Products");
		
		// Fetch the product from database with id
		Product nProduct = productDAO.get(id);

		modelAndView.addObject("product", nProduct);

		return modelAndView;
	}

	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct, BindingResult results, Model model, HttpServletRequest request) {
		
		if(mProduct.getId() == 0) {
			new ProductValidator().validate(mProduct, results);
		} else {
			if(!mProduct.getFile().getOriginalFilename().equals("")) {
				new ProductValidator().validate(mProduct, results);
			}
		}
		// Checking for any error
		if(results.hasErrors()) {
			model.addAttribute("userClickManageProducts", true);
			model.addAttribute("title", "Manage Products");
			model.addAttribute("message", "Please provide correct data and make sure that all required fields are not empty!");
			
			// If we use redirect in the return string, the error message won't be displayed; so we return the page directly 
			return "page";
		}
		

		logger.info(mProduct.toString());

		if(mProduct.getId() == 0) {
			// Create new Product
			productDAO.addProduct(mProduct);
		} else {
			// Update an existing Product
			productDAO.updateProduct(mProduct);
		}
		
		if(!mProduct.getFile().getOriginalFilename().equals("")) {
			FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode());
		}
		return "redirect:/manage/products?operation=product";
	}
	
	@RequestMapping(value = "/product/{id}/activation", method = RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id) {
		Product product = productDAO.get(id);
		boolean isActive = product.isActive();
		product.setActive(!product.isActive());
		productDAO.updateProduct(product);
		return (isActive)?  "Product " + product.getId() + "Deactivated Successfully!" : "Product " + product.getId() + "Activated Successfully!";
	}
	
	@RequestMapping(value="/category", method = RequestMethod.POST)
	public String handleCategorySubmission(@ModelAttribute Category category) {
		categoryDAO.addCategory(category);
		return "redirect:/manage/products?operation=category";
	}

	// Retrieve Category LOV list
	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return categoryDAO.list();
	}
	
	
	// New Catgeory Object for the 'New Category Modal'
	@ModelAttribute("category")
	public Category getCategory() {
		return new Category();
	}
}
