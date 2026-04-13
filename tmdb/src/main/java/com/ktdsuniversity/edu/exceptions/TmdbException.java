package com.ktdsuniversity.edu.exceptions;

public class TmdbException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4692366451571646519L;
	
	private String errorPage;
	
	private Object object;
	
	public TmdbException(String message, String errorPage) {
		
		super(message);
		this.errorPage = errorPage;
		
	}
	
	public TmdbException(String message, String errorPage, Object object) {
		
		super(message);
		this.errorPage = errorPage;
		this.object = object;
		
	}
	
	
	

	public String getErrorPage() {
		return this.errorPage;
	}

	public Object getObject() {
		return this.object;
	}
	
	

}
