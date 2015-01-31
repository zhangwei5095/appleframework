/**
 *
 * 日    期：12-2-14
 */
package com.appleframework.exception.error;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 功能说明：
 * </pre>
 * 
 * @author 徐少敏
 * @version 1.0
 */
public class SimpleMainError implements AppleMainError {

	private String code;

	private String message;

	private String solution;

	private List<AppleSubError> subErrors = new ArrayList<AppleSubError>();

	public SimpleMainError(String code) {
		this.code = code;
	}
	
	public SimpleMainError(String code, String message, String solution) {
		this.code = code;
		this.message = message;
		this.solution = solution;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public String getSolution() {
		return solution;
	}

	public List<AppleSubError> getSubErrors() {
		return this.subErrors;
	}

	public void setSubErrors(List<AppleSubError> subErrors) {
		this.subErrors = subErrors;
	}

	public AppleMainError addSubError(AppleSubError subError) {
		this.subErrors.add(subError);
		return this;
	}
}
