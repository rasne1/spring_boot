package com.ktdsuniversity.edu.board.vo.request;

/*
 * 
 * 게시글 검색사용
 * 게시글 페이지네이션 사용.
 * */

public class SearchListVO {
	
	// 목록을 보여줄 페이지의 번호 ( 0-base)
	private int pageNo;
	
	// 하나의 페이지에 보여줄 게시글의 개수 
	private int listSize;
	
	// 총 몇개의 페이지가 생성되느냐
	// 올림 (게시글의 개수/ liseSize)
	private int pageCount;
	
	// 하나의 페이지 그룹에 보여줄 페이지의 개수.
	private int pageCountIntGroup;
	//페이지 그룹의 개수: 올림( pageCount / pageCountIntGroup)
	private int pageGroupCount;
	
	//현재 노출되고 있는 페이지 번호가 속한 그룹의 번호
	private int groupNo;
	
	// 현재 노출되고 있는 페이지 그룹의 시작 페이지 번호
	private int groupStartPageNo;
	
	//현재 노출되고있는 페이지 그룹의 마지막 페이지 번호
	private int groupEndPageNo;
	
	//현재 노출되고 있는 페이지 그룹의 다음 그룹이 있는지 여부
	private boolean hasNextPageGroup;
	// 현재 노출되고있는 페이지 그룹의 이전 그룹이 있는지 여부
	private boolean hasPrevPageGroup;
	
	private int nextPageGroupStartPageNO;
	//현재 노출되고 있는 페이지 그룹의 다음 그룹 페이지 시작 번호
	private int prevPageGroupStartPageNo;

	private String searchType;
	
	private String searchKeyword;
	
	
	// listSize의 기본값 할당을 위한 생성자.
	public SearchListVO() {
		// 한페이지에 10개의 게시글이 노출되도록 설정.
		this.listSize = 10;
		this.pageCountIntGroup = 10;
	}
	
	
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

	public int getPageCount() {
		return this.pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
	
	
	
	/*
	 * 조회된 게시글의 개수와 listSize를 이횽해 총 몇개의 페이지가 필요한지 계산.
	 * @param articleCount 게시글의 개수
	 * 
	 * */
	
	public String getSearchType() {
		return searchType;
	}


	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}


	public String getSearchKeyword() {
		return searchKeyword;
	}


	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}


	public void computePagination(int articleCount) {
		//페이지의 개수 계산.
		this.pageCount = (int)Math.ceil(articleCount/(double) this.listSize);
		
		//페이지를 페이지네이션 하기 위한 계산.
		// 페이지 그룹의 개수 계산.
		// (올림)페이지 개수 / 페이지 그룹당 페이지 개수
		this.pageGroupCount = (int) Math.ceil(this.pageCount/(double) this.pageCountIntGroup);
		// 현재 페이지 그룹번호 계산.
		// 현재 페이지 번호 / 페이지 그룹당 페이지 개수
		this.groupNo= this.pageNo / this.pageCountIntGroup;
		
		// 현재페이지 그룹의 시작 페이지 번호 계산.
		// 현재 페이지 그룹번호 * 페이지 그룹당 페이지 개수
		this.groupStartPageNo = this.groupNo * this.pageCountIntGroup;
		
		// 현재 페이지 그룹의 마지막 페이지 번호 계산.
		//(현재 페이지 그룹 번호+1) * 페이지 그룹당 페이지 개수 -1
		this.groupEndPageNo = (this.groupNo+1)*this.pageCountIntGroup-1;
		// 마지막 페이지 번호 보정.
		// 현재 페이지 그룹의 마ㅣㅈ막 페이지 번호가 총페이지 개수보다 클 경우 보정 필요.
		if(this.groupEndPageNo > this.pageCount) {
			this.groupEndPageNo = this.pageCount-1;
		}
	
		// 다음 그룹이 존재하는지 계싼.
		// 현재 페이지 그룹 < 총 페이지 그룹 개수 -1
		this.hasNextPageGroup = this.groupNo < this.pageGroupCount -1;
		
		
		// 이전 그룹이 존재하는지 계산.
		// 현재 페이지 그룹 >0
		this.hasPrevPageGroup = this.groupNo>0;
		
		// 다음 그룹의 시작 페이지 번호 계산.
		// 현재 페이지 그룹의 마지막 페이지 번호 +1
		this.nextPageGroupStartPageNO = this.groupEndPageNo +1;
		
		// 이전 그룹의 시작 페이지 번호 계산.
		// 현재 페이지 그룹의 시작 페이지 번호 - 페이지 그룹당 페이지의 개수
		this.prevPageGroupStartPageNo = this.groupStartPageNo - this.pageCountIntGroup;
	}


	public int getPageCountIntGroup() {
		return this.pageCountIntGroup;
	}


	public void setPageCountIntGroup(int pageCountIntGroup) {
		this.pageCountIntGroup = pageCountIntGroup;
	}


	public int getPageGroupCount() {
		return this.pageGroupCount;
	}


	public void setPageGroupCount(int pageGroupCount) {
		this.pageGroupCount = pageGroupCount;
	}


	public int getGroupNo() {
		return this.groupNo;
	}


	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}


	public int getGroupStartPageNo() {
		return this.groupStartPageNo;
	}


	public void setGroupStartPageNo(int groupStartPageNo) {
		this.groupStartPageNo = groupStartPageNo;
	}


	public int getGroupEndPageNo() {
		return this.groupEndPageNo;
	}


	public void setGroupEndPageNo(int groupEndPageNo) {
		this.groupEndPageNo = groupEndPageNo;
	}


	public boolean isHasNextPageGroup() {
		return this.hasNextPageGroup;
	}


	public void setHasNextPageGroup(boolean hasNextPageGroup) {
		this.hasNextPageGroup = hasNextPageGroup;
	}


	public boolean isHasPrevPageGroup() {
		return this.hasPrevPageGroup;
	}


	public void setHasPrevPageGroup(boolean hasPrevPageGroup) {
		this.hasPrevPageGroup = hasPrevPageGroup;
	}


	public int getNextPageGroupStartPageNO() {
		return this.nextPageGroupStartPageNO;
	}


	public void setNextPageGroupStartPageNO(int nextPageGroupStartPageNO) {
		this.nextPageGroupStartPageNO = nextPageGroupStartPageNO;
	}


	public int getPrevPageGroupStartPageNo() {
		return this.prevPageGroupStartPageNo;
	}


	public void setPrevPageGroupStartPageNo(int prevPageGroupStartPageNo) {
		this.prevPageGroupStartPageNo = prevPageGroupStartPageNo;
	}
	
}
