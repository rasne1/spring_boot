package com.ktdsuniversity.edu.actor.vo;

import java.util.List;

import com.ktdsuniversity.edu.files.vo.FilesVO;

public class ActorVO {
	
	private String actorId;
	private String actorName;
	private String actorProfileUrl;
	
	private List<FilesVO> files;

	
	public String getActorId() {
		return this.actorId;
	}

	public void setActorId(String actorId) {
		this.actorId = actorId;
	}

	public String getActorName() {
		return this.actorName;
	}

	public void setActorName(String actorName) {
		this.actorName = actorName;
	}

	public String getActorProfileUrl() {
		return this.actorProfileUrl;
	}

	public void setActorProfileUrl(String actorProfileUrl) {
		this.actorProfileUrl = actorProfileUrl;
	}

	public List<FilesVO> getFiles() {
		return this.files;
	}

	public void setFiles(List<FilesVO> files) {
		this.files = files;
	}
	
	
	
	
}
