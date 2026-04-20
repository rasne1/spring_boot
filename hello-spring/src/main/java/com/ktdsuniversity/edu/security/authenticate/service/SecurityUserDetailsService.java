package com.ktdsuniversity.edu.security.authenticate.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ktdsuniversity.edu.members.dao.MembersDao;
import com.ktdsuniversity.edu.members.vo.MembersVO;
import com.ktdsuniversity.edu.security.user.SecurityUser;

public class SecurityUserDetailsService implements UserDetailsService {
	
	
	private MembersDao membersDao;
	
	//생성자
	public SecurityUserDetailsService(MembersDao membersDao) {
		this.membersDao = membersDao;
	}

	@Override   // 사용자의 이메일로 DB에서 회원의 정보를 조회.
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// DB에서 회원의 정보를 가져와 MembersVO 형태로 loadedUser 에 저장 
		MembersVO loadedUser =this.membersDao.selectMemberByEmail(username);
		
		// 없는 경우 예외처리
		if(loadedUser == null) {
			throw new UsernameNotFoundException("아이디 또는 비밀번호 안맞음.");
		}
		
		// 권한의 종류가 하나 이상일 경우가 있어 조회한 권한을 List에 저장
		List<String> userRole =this.membersDao.selectMemberRolesByEmail(username);
		loadedUser.setRoles(userRole);
		
		
		return new SecurityUser(loadedUser);
	}

}
