package com.ktdsuniversity.edu.tmdb.vo.request;

public class SearchListVO {
	
	
	private int pageNo = 0;
	
	private int listSize = 10; 
	

	public int getPageNo() {
		return this.pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getListSize() {
		return this.listSize;
	}

	public void setListSize(int listSize) {
		this.listSize = listSize;
	}
	

	

}
