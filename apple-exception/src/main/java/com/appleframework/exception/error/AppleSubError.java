/**
 *
 * 日    期：12-2-12
 */
package com.appleframework.exception.error;

import java.io.Serializable;

import javax.xml.bind.annotation.*;

/**
 * <pre>
 * 功能说明：
 * </pre>
 * 
 * @author 徐少敏
 * @version 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "subError")
public class AppleSubError implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlAttribute
	private String code;

	@XmlElement
	private String message;

	public AppleSubError() {
	}
	
	public AppleSubError(String code) {
		this.code = code;
	}

	public AppleSubError(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
