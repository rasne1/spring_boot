package com.ktdsuniversity.edu.members.vo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class MembersVO {

	@NotEmpty(message="아이디 입력해라")
	private String name;
	@Pattern(regexp="^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$"
	         ,message="비밀번호는 거 다른데 하던대로 ")
	private String password;
	@NotEmpty(message="이메일! 입력!")
	private String email;
	private String salt;
	
	private String registDate;
    private String modifyDate;
    private String latestPasswordChangeDate;
    private String loginDate;
    private String latestLoginIp;
    private int loginFailCount;
    private String latestLoginFailDate;
    private String blockYn;
    private String fileGroupId;
	
    
    
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRegistDate() {
		return registDate;
	}
	public void setRegistDate(String registDate) {
		this.registDate = registDate;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getLatestPasswordChangeDate() {
		return latestPasswordChangeDate;
	}
	public void setLatestPasswordChangeDate(String latestPasswordChangeDate) {
		this.latestPasswordChangeDate = latestPasswordChangeDate;
	}
	public String getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}
	public String getLatestLoginIp() {
		return latestLoginIp;
	}
	public void setLatestLoginIp(String latestLoginIp) {
		this.latestLoginIp = latestLoginIp;
	}
	public int getLoginFailCount() {
		return loginFailCount;
	}
	public void setLoginFailCount(int loginFailCount) {
		this.loginFailCount = loginFailCount;
	}
	public String getLatestLoginFailDate() {
		return latestLoginFailDate;
	}
	public void setLatestLoginFailDate(String latestLoginFailDate) {
		this.latestLoginFailDate = latestLoginFailDate;
	}
	public String getBlockYn() {
		return blockYn;
	}
	public void setBlockYn(String blockYn) {
		this.blockYn = blockYn;
	}
	public String getFileGroupId() {
		return fileGroupId;
	}
	public void setFileGroupId(String fileGroupId) {
		this.fileGroupId = fileGroupId;
	}
	
	
	
	
}
