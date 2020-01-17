package com.khaled.BipFrontend.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

	// 404 Error (Page not found) handler.
	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handlerNoHandlerFoundException() {
		ModelAndView modelAndView = new ModelAndView("error");

		modelAndView.addObject("errorTitle", "The page is not constructed!");
		modelAndView.addObject("errorDescription", "The page you are looking for is not available now!");
		modelAndView.addObject("title", "404 Error Page");

		return modelAndView;
	}

	// 404 Error (Product not found) handler.
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handlerProductNotFoundException() {
		ModelAndView modelAndView = new ModelAndView("error");

		modelAndView.addObject("errorTitle", "Product is not available right row!");
		modelAndView.addObject("errorDescription", "The product you are looking for is not available now!");
		modelAndView.addObject("title", "Product Unavailable");

		return modelAndView;
	}

	// 404 Error (Product not found) handler.
	@ExceptionHandler(Exception.class)
	public ModelAndView handlerException(Exception ex) {
		ModelAndView modelAndView = new ModelAndView("error");
		
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		ex.printStackTrace(printWriter);

		modelAndView.addObject("errorTitle", "Something went wrong, Please contact your administrator!");
		modelAndView.addObject("errorDescription", stringWriter.toString());
		modelAndView.addObject("title", "ERROR");

		return modelAndView;
	}
}
