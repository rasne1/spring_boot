package com.ktdsuniversity.edu.exceptions.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ktdsuniversity.edu.exceptions.TmdbException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(TmdbException.class)
	public String viewErrorPage(TmdbException tbe, Model model) {
		
		logger.error(tbe.getMessage(), tbe);
		
		String errorPage = tbe.getErrorPage();
		String message = tbe.getMessage();
		model.addAttribute("errorMessage", message);
		Object modelData = tbe.getObject();
		
		if(modelData != null) {
			model.addAttribute("errorData", modelData);
		}
		

		return errorPage;
		
	}
	
	@ExceptionHandler(RuntimeException.class)
	public String viewSystemErrorPage(RuntimeException re) {
		logger.error(re.getMessage(), re);
		return "errors/500";
		
	}

}
