package com.appleframework.exception;

/**
 * Base class for all custom exception thrown in AppleFramework
 * 
 * @author Cruise.Xu
 * @date: 2012-10-15
 *
 */
public class DAOException extends Exception {
	
	/**
     * uid used for serialization
     */
	private static final long serialVersionUID = 7696865849245536841L;
		
	/**
     * Exception message 
     */
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}

