package com.ktdsuniversity.edu.members.vo.response;

import java.util.List;

import com.ktdsuniversity.edu.members.vo.request.RegistVO;

/**
 * 게시글 검색결과를 담고 있는 클래스.
 * 게시글 목록
 * 게시글 개수
 */
public class SearchResultVO {
	private List<RegistVO> result;
	private int count;
	
	public List<RegistVO> getResult() {
		return this.result;
	}
	public void setResult(List<RegistVO> result) {
		this.result = result;
	}
	public int getCount() {
		return this.count;
	}
	public void setCount(int count) {
		this.count = count;
	}

}
