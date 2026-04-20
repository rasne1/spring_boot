package com.ktdsuniversity.edu.security.authenticate.handlers;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.ktdsuniversity.edu.members.dao.MembersDao;
import com.ktdsuniversity.edu.members.vo.request.LoginVO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginFailureHandler implements AuthenticationFailureHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginFailureHandler.class);
	
	private MembersDao membersDao;
	
	public LoginFailureHandler(MembersDao membersDao) {
		
		this.membersDao = membersDao;
		
	}
	
	
	

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		logger.error(exception.getMessage(), exception);
		// 사용자가 보낸 email 
		String email = request.getParameter("email");
		
		// 로그인 실패의 이유가 비밀번호 오류 라면 실행 
		if(exception instanceof BadCredentialsException) {
			this.membersDao.updateIncreaseLoginFailCount(email);
			this.membersDao.updateBlock(email);
		}
		
		// 로그인 페이지 경로 
		String loginPagePath = "/WEB-INF/views/members/login.jsp";
		
		//로그인페이지로 이동준비
		RequestDispatcher dispatcher = request.getRequestDispatcher(loginPagePath);
		
		LoginVO loginVO = new LoginVO();
		
		//로그인페이지에 이메일을 전달 
		loginVO.setEmail(email);
		
		//로그인 페이지에 다시쓰지않게 이메일 전달하고 
		request.setAttribute("inputData", loginVO);
		
		// 오류메세지 전달
		request.setAttribute("errorMessage", exception.getMessage());
		
		// 페이지의 url을 바꾸지않고 로그인페이지로 이동 
		dispatcher.forward(request, response);
		
		
	}

}
