package com.ktdsuniversity.edu.security.authenticate.oauth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.ktdsuniversity.edu.members.vo.MembersVO;
import com.ktdsuniversity.edu.security.user.SecurityUser;

public class NaverOAuthUserDetails extends SecurityUser implements OAuth2User{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5338722101741827393L;
	
	private Map<String,Object> oAuthResult;
	
	public NaverOAuthUserDetails(MembersVO membersVO, Map<String,Object> oAuthResult) {
		super(membersVO); // SecurityUser 에 있는 생성자 호출
		this.oAuthResult = (Map<String,Object>) oAuthResult.get("response");
		membersVO.setEmail(this.oAuthResult.get("email").toString());
		membersVO.setName(this.oAuthResult.get("name").toString());
		
		List<String> userRoles = new ArrayList<>();
		userRoles.add("RL-20260414-000003");
		membersVO.setRoles(userRoles);
	}
	
	public String getEmail() {
		return super.getMembersVO().getEmail();
	}
	
	
	@Override
	public Map<String, Object> getAttributes() {
		return this.oAuthResult;
	}


	@Override
	public String getName() {
		return super.getMembersVO().getName();
	}

}
