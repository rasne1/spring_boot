package com.ktdsuniversity.edu.security.authenticate.service;

import org.jspecify.annotations.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ktdsuniversity.edu.members.helpers.SHA256Util;

public class SecurityPasswordEncoder implements PasswordEncoder {

	@Override
	public @Nullable String encode(@Nullable CharSequence rawPassword) {
		return null;
	}

	@Override
	public boolean matches(@Nullable CharSequence rawPassword, @Nullable String encodedPassword) {
		return false;
	}
	
	
	// 암호화가 안된 비밀번호를 암호화
	public String encode(String rawPassword, String salt) {
		
		return SHA256Util.getEncrypt(rawPassword, salt);
	}
	
	//암호화된 비밀번호와 DB에 저장된 비밀번호를 비교
	public boolean matches(String rawPassword, String salt, String encodePassword) {
		
		return this.encode(rawPassword, salt).equals(encodePassword);
	}

}
