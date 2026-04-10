package com.ktdsuniversity.edu.replies.vo;

import java.util.List;

import com.ktdsuniversity.edu.files.vo.FilesVO;
import com.ktdsuniversity.edu.members.vo.MembersVO;

public class RepliesVO {

	private String articleId;
	private String crtDt;
	private String email;
	private String fileGroupId;
	private String id;
	private String mdfyDt;
	private String parentReplyId;
	private int recommendCnt;
	private String reply;
	
	
	private MembersVO membersVO;
	
	private int level;
	private List<FilesVO> files;
	
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public String getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(String crtDt) {
		this.crtDt = crtDt;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFileGroupId() {
		return fileGroupId;
	}
	public void setFileGroupId(String fileGroupId) {
		this.fileGroupId = fileGroupId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMdfyDt() {
		return mdfyDt;
	}
	public void setMdfyDt(String mdfyDt) {
		this.mdfyDt = mdfyDt;
	}
	public String getParentReplyId() {
		return parentReplyId;
	}
	public void setParentReplyId(String parentReplyId) {
		this.parentReplyId = parentReplyId;
	}
	public int getRecommendCnt() {
		return recommendCnt;
	}
	public void setRecommendCnt(int recommendCnt) {
		this.recommendCnt = recommendCnt;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public MembersVO getMembersVO() {
		return this.membersVO;
	}
	public void setMembersVO(MembersVO membersVO) {
		this.membersVO = membersVO;
	}
	public int getLevel() {
		return this.level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public List<FilesVO> getFiles() {
		return this.files;
	}
	public void setFiles(List<FilesVO> files) {
		this.files = files;
	}
	
	
}
