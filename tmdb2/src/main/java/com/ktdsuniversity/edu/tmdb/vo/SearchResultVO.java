package com.ktdsuniversity.edu.tmdb.vo;

import java.util.List;

public class SearchResultVO {

	private List<TmdbVO> result;
	private int count;
	
	
	
	
	public List<TmdbVO> getResult() {
		return result;
	}
	public void setResult(List<TmdbVO> result) {
		this.result = result;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
