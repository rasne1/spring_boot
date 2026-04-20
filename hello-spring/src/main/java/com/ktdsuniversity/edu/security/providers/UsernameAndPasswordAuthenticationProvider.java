package com.ktdsuniversity.edu.security.providers;

import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ktdsuniversity.edu.members.vo.MembersVO;
import com.ktdsuniversity.edu.security.authenticate.service.SecurityPasswordEncoder;
import com.ktdsuniversity.edu.security.user.SecurityUser;

public class UsernameAndPasswordAuthenticationProvider implements AuthenticationProvider  {
	
	
	// 사용자 로그인정보 조회
	private UserDetailsService userDetailsService;
	
	// 사용자 로그인시 보낸 비밀번호 와 DB에 있는 비밀번호 비교용.
	private PasswordEncoder passwordEncoder;
	
	
	
	//검증에 필요한 정보를 위한 생성자
	public UsernameAndPasswordAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
	} 
	

	@Override
	public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		// 사용자가 보낸 로그인 정보 아이디(이메일) 가져온다.
		String email = authentication.getName();
		
		// 이메일을 이용하여 유저의 정보를 DB에서 찾아온다.
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
		
		// DB에서 찾아온 사용자의 계정이 잠겨있다면 예외 반환
		if(!userDetails.isAccountNonLocked()) {
			throw new LockedException("아이디 또는 비밀번호가 안맞음");
		}
		
		// 사용자가 보낸 로그인정보중 비밀번호를 꺼내서 문자열로 저장
		String rawPassword = authentication.getCredentials().toString();
		
		// 서비스에서 userDetails에 넣어준 SecurityUser가 있지만 타입이 달라 getMembersVO를 사용할수없어 형변환후 저장
		MembersVO membersVO = ((SecurityUser) userDetails).getMembersVO();
		
		// 인터페이스인 PasswordEncoder 안에 없는 메소드를 사용을 위한 형변환
		SecurityPasswordEncoder passwordComparator =(SecurityPasswordEncoder) this.passwordEncoder;
		
		//비밀번호를 Encoder에 보내 암호화한 값을 받아 암호화된 비밀번호와 DB에있는 비밀번호 비교
		boolean isMatch =passwordComparator.matches(rawPassword, membersVO.getSalt(), userDetails.getPassword());
		
		//비밀번호 비교시 예외처리
		if(!isMatch) {
			throw new BadCredentialsException("아이디 또는 비밀번호가 일치하지 않습니다");
		}
		
		
		
		// 인증토큰에 사용자, 사용자의 비밀번호, 사용자의 권한을 담아 SecurityContext에 저장.
		return new UsernamePasswordAuthenticationToken(membersVO, userDetails.getPassword(), userDetails.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		// 로그인타입이 UsernamePasswordAuthenticationToken 라면 이 provider 사용 
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
