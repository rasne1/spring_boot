package com.ktdsuniversity.edu.exceptions.handlers;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktdsuniversity.edu.board.web.BoardController;
import com.ktdsuniversity.edu.exceptions.HelloSpringApiException;
import com.ktdsuniversity.edu.exceptions.HelloSpringException;

/*
 * Spring Application에서 던져져 catch되지 않은
 * 예외들을 처리하는 클래스.
 * 
 * @Controller 와 유사한 형태
 * ==>URL이 endpoint 
 * 
 * @ControllerAdvice
 * ==> Exception이 endpoint
 * 
 * */

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	/*
	 * helloSpringException 이 던져지면,
	 * viewErrorPage가 실행된다.
	 * 실행된 결과는 ModelAndView가 된다.
	 * 
	 *  @return 사용자에게 보여줄 템플릿의 이름.
	 * */
	
	
	
	@ExceptionHandler(HelloSpringException.class)
	public String viewErrorPage(HelloSpringException hse, Model model) {
		
		logger.error(hse.getMessage(), hse);
		
		String errorPage = hse.getErrorPage();
		String message = hse.getMessage();
		model.addAttribute("errorMessage", message);
		Object modelData = hse.getObject();
		if(modelData != null) {
			model.addAttribute("errorData", modelData);
		}
		
		return errorPage;
	}
	@ResponseBody
	@ExceptionHandler(HelloSpringApiException.class)
	public Map<String, Object> returnErrorJson(HelloSpringApiException hsae){
		logger.debug(hsae.getMessage(), hsae);

		int status = hsae.getErrorStatus();
		Object errorObject = hsae.getError();
		
		Map<String, Object> responseData = new HashMap<>();
		responseData.put("status", status);
		responseData.put("error", errorObject);
		
		return responseData;
		
	}
	
	@ExceptionHandler(RuntimeException.class)
	public String viewSystemErrorPage(RuntimeException re) {
		logger.error(re.getMessage(), re);
		return "error/500";
	}
	
}
