package com.ktdsuniversity.edu.members.vo.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegistVO {
	
	@NotBlank(message="이메일을 입력하세요")
	@Email(message="이메일 형태가 아닙니다.")
	private String email;
	@NotBlank(message="이름을 입력하세요")
	@Size(min = 2, message="이름은 2글자 이상")
	private String name;
	@Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$"
	   , message = "비밀번호는 영소문자, 영대문자, 숫자 최소 1개를 포함하여 8글자 이상으로 입력하세요.")
	private String password;
	private String salt;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}

}
