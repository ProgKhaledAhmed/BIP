package com.khaled.BipFrontend.exception;

import java.io.Serializable;

public class ProductNotFoundException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public ProductNotFoundException() {
		this("Product is currently not availble!");
	}

	public ProductNotFoundException(String message) {
		this.message = System.currentTimeMillis() + ": " + message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = System.currentTimeMillis() + ": " + message;
	}

}
