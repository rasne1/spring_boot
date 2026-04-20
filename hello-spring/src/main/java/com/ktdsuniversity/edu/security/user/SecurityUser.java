package com.ktdsuniversity.edu.security.user;

import java.util.Collection;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ktdsuniversity.edu.members.vo.MembersVO;

public class SecurityUser implements UserDetails{

	
	private static final long serialVersionUID = 7907191462472441568L;
	
	// MembersVO 형태를 담을 변수
	private MembersVO membersVO;
	
	// SecurityUser 에 MembersVO를 담기위한 생성자
	public SecurityUser(MembersVO membersVO) {
		this.membersVO = membersVO;
	}

	// SecurityUser에서 MembersVO를 꺼내기 위한 getter
	public MembersVO getMembersVO() {
		return membersVO;
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		//SpringSecurity가 권한을 인식할수있게 변환후 반환
		return this.membersVO.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_"+role)).toList();
	}

	@Override
	public @Nullable String getPassword() {
		
		return this.membersVO.getPassword();
	}

	@Override
	public String getUsername() {
		
		return this.membersVO.getEmail();
	}

}
