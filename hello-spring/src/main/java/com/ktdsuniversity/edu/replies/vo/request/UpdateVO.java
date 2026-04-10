package com.ktdsuniversity.edu.replies.vo.request;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;

public class UpdateVO {
	
	@NotBlank(message="댓글 내용을 입력하세요")
	private String content;
	private List<Integer> delFileNum;
	private List<MultipartFile> neAttachFile;
	
	
	public String getContent() {
		return this.content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<Integer> getDelFileNum() {
		return this.delFileNum;
	}
	public void setDelFileNum(List<Integer> delFileNum) {
		this.delFileNum = delFileNum;
	}
	public List<MultipartFile> getNeAttachFile() {
		return this.neAttachFile;
	}
	public void setNeAttachFile(List<MultipartFile> neAttachFile) {
		this.neAttachFile = neAttachFile;
	}
	public void setReplyId(String replyId) {
		// TODO Auto-generated method stub
		
	}
	
	

}
