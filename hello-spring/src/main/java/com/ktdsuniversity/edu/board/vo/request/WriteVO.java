package com.ktdsuniversity.edu.board.vo.request;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

/* 
 * 게시글 등록을 위해 
 * 브라우저에서 컨트롤러(엔드포인트)로 전송되는
 * 파라미터를 받아오기 위한 클래스
 * 
 * spring이 파라미터를 writeVO의 멤버변수로 할당할때 
 * setter를 이용한다.
 * */



public class WriteVO {
	
	private String id;
	
	@NotEmpty(message="제목 입력해라")
	@Size(min = 3, message = "제목은 3글자 이상 입력.")
	private String subject;
	@NotEmpty(message="이메일 입력하라고")
	@Email(message="이메일 이라니까")
	private String email;
	private String content;
	
	private List<MultipartFile> attachFile;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSubject() {
		return this.subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContent() {
		return this.content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<MultipartFile> getAttachFile() {
		return attachFile;
	}
	public void setAttachFile(List<MultipartFile> attachFile) {
		this.attachFile = attachFile;
	}
	
	
	
}
