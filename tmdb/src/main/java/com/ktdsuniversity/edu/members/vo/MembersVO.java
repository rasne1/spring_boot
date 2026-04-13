package com.ktdsuniversity.edu.members.vo;

import java.time.LocalDate;

public class MembersVO {
	
	private String email;
	private String name;
	private String password;
	private String salt;
	private LocalDate registDate;
	private LocalDate modifyDate;
	private LocalDate latestPasswordChangeDate;
	private LocalDate loginDate;
	private String latestLoginIp;
	private int loginFailCount;
	private LocalDate latestLoginFailDate;
	private String blockYn;
	private String fileGroupId;
	
	
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return this.salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public LocalDate getRegistDate() {
		return this.registDate;
	}
	public void setRegistDate(LocalDate registDate) {
		this.registDate = registDate;
	}
	public LocalDate getModifyDate() {
		return this.modifyDate;
	}
	public void setModifyDate(LocalDate modifyDate) {
		this.modifyDate = modifyDate;
	}
	public LocalDate getLatestPasswordChangeDate() {
		return this.latestPasswordChangeDate;
	}
	public void setLatestPasswordChangeDate(LocalDate latestPasswordChangeDate) {
		this.latestPasswordChangeDate = latestPasswordChangeDate;
	}
	public LocalDate getLoginDate() {
		return this.loginDate;
	}
	public void setLoginDate(LocalDate loginDate) {
		this.loginDate = loginDate;
	}
	public String getLatestLoginIp() {
		return this.latestLoginIp;
	}
	public void setLatestLoginIp(String latestLoginIp) {
		this.latestLoginIp = latestLoginIp;
	}
	public int getLoginFailCount() {
		return this.loginFailCount;
	}
	public void setLoginFailCount(int loginFailCount) {
		this.loginFailCount = loginFailCount;
	}
	public LocalDate getLatestLoginFailDate() {
		return this.latestLoginFailDate;
	}
	public void setLatestLoginFailDate(LocalDate latestLoginFailDate) {
		this.latestLoginFailDate = latestLoginFailDate;
	}
	public String getBlockYn() {
		return this.blockYn;
	}
	public void setBlockYn(String blockYn) {
		this.blockYn = blockYn;
	}
	public String getFileGroupId() {
		return this.fileGroupId;
	}
	public void setFileGroupId(String fileGroupId) {
		this.fileGroupId = fileGroupId;
	}
	
	
}
