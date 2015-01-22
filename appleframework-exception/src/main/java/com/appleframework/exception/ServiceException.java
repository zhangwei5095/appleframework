package com.appleframework.exception;

/**
 * Base class for all custom exception thrown in AppleFramework
 * 
 * @author Cruise.Xu
 * @date: 2012-10-15
 * 
 */
public class ServiceException extends Exception {

	private static final long serialVersionUID = 7696865849245536841L;

	public static final String RSP = "rsp.";

	private String code;

	private String key;

	private String message;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ServiceException() {
		super();
	}

	public ServiceException(String code) {
		this.code = code;
	}

	public ServiceException(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public ServiceException(String code, String key, String message) {
		this.code = code;
		this.key = key;
		this.message = message;
	}

	public ServiceException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public ServiceException(String code, String message, Throwable throwable) {
		super(message, throwable);
		this.code = code;
		this.message = message;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getMessage() {
		return message;
	}

}
