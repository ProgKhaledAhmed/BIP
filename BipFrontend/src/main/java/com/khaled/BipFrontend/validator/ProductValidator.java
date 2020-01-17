package com.khaled.BipFrontend.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.khaled.BipBackend.dto.Product;

public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Product.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Product product = (Product) target;
		
		if(product.getFile() == null || product.getFile().getOriginalFilename().equals("")) {
			errors.rejectValue("file", null, "Please select an image for the product!");
			return;
		}
		
		if(!(product.getFile().getContentType().equals("image/jpeg") || product.getFile().getContentType().equals("image/png") || product.getFile().getContentType().equals("image/gif"))) {
			errors.rejectValue("file", null , "Images(jpeg, png, gif, ...) are only allowed!");
			return;
		}
	}

}
