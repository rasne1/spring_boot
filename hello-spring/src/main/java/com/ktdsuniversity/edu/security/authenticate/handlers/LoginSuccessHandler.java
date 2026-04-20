package com.ktdsuniversity.edu.security.authenticate.handlers;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.ktdsuniversity.edu.common.utils.StringUtils;
import com.ktdsuniversity.edu.members.dao.MembersDao;
import com.ktdsuniversity.edu.members.vo.request.LoginVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	
	//멤벼변수
	private MembersDao membersDao;
	
	//LoginSuccessHandler 에 membersDao 정보를 저장
	public LoginSuccessHandler(MembersDao membersDao) {
		this.membersDao = membersDao;
	}
	
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		//loginVO 생성
		LoginVO loginVO = new LoginVO();
		// 사용자의 ip 저장
		loginVO.setIp(request.getRemoteAddr());
		// 사용자의 이메일 저장
		loginVO.setEmail(authentication.getName());
		// loginVO 기반으로 로그인 성공 업데이트 요청
		this.membersDao.updateSuccessLogin(loginVO);
		
		// 사용자가 가려고했던 url
		String go = request.getParameter("go");
		
		// 가고자했던 페이지가 있다면 그페이지로 보내주고 아니면 "/"페이지로 Url 새로고침해서 보내준다.
		response.sendRedirect(StringUtils.emptyTo(go, "/"));
		
		
	}

}
