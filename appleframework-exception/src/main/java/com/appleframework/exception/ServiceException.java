/*
 * CloudframeworkException.java
 *
 * 深圳广联赛讯有限公司
 *
 * Copyright (C) 2012 WONDERSHARE.COM
 *
 * All Right reserved
 * http://www.glsx.com.cn
 */
package com.appleframework.exception;

import com.appleframework.config.core.PropertyConfigurer;

/**
 * Base class for all custom exception thrown in CloudFramework
 * 
 * @author Cruise.Xu
 * @date: 2012-10-15
 *
 */
public class ServiceException extends Exception {
	
	/**
     * uid used for serialization
     */
	private static final long serialVersionUID = 7696865849245536841L;
	
	/**
     * Exception code 
     */
	private String code;
	
	/**
     * Exception message 
     */
	private String message;
	
    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
		if(null != code && null != this.message) {
    		this.message = PropertyConfigurer.getValue(code);
    	}
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
     * Default constructor
     */
    public ServiceException() {
        super();
    }

    /**
     * Create a new CloudframeworkException from a message which explain the nature of
     * the Exception
     */
    public ServiceException(String code) {
    	this.code = code;
    	if(null != code) {
    		this.message = PropertyConfigurer.getValue(code);
    	}
    }

    public ServiceException(String code, String message) {
    	this.code = code;
        this.message = message;;
        
    }
    /**
     * Create a new CloudframeworkException from a message and a base throwable
     * exception
     */
    public ServiceException(String message, Throwable throwable) {
        super(message, throwable);
    }
    
    public ServiceException(String code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
        this.message = message;
    }
    
    public ServiceException(ServiceErrorType serviceErrorType) {
		super(ServiceErrorType.msgValue(serviceErrorType));
		this.code = ServiceErrorType.codeValue(serviceErrorType);
	}
	
	public ServiceException(ServiceErrorType serviceErrorType, Throwable throwable) {
		super(ServiceErrorType.msgValue(serviceErrorType), throwable);
		this.code = ServiceErrorType.codeValue(serviceErrorType);
	}
	
	public ServiceException(ServiceErrorType serviceErrorType, String message, Throwable throwable) {
		super(ServiceErrorType.msgValue(serviceErrorType) + ":" + message, throwable);
		this.code = ServiceErrorType.codeValue(serviceErrorType);
	}

	@Override
	public String getMessage() {
		if(null != super.getMessage()) {
			return super.getMessage();
		}
		if(null != this.message) {
			return this.message;
		}
		if(null != code) 
			return PropertyConfigurer.getValue(code);
		return this.message;
	}
	
}

