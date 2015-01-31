/**
 *
 * 日    期：12-2-10
 */
package com.appleframework.exception;

import javax.xml.bind.annotation.*;

import com.appleframework.exception.error.AppleMainError;
import com.appleframework.exception.error.AppleMainErrorType;
import com.appleframework.exception.error.AppleMainErrors;
import com.appleframework.exception.error.AppleSubError;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author 徐少敏
 * @version 1.0
 */
public class BaseException extends Exception {

	private static final long serialVersionUID = -4379801359412979859L;

	@XmlAttribute
    protected String code;

    @XmlElement
    protected String message;

    @XmlElement
    protected String solution;

    @XmlElementWrapper(name = "subErrors")
    @XmlElement(name = "subError")
    protected List<AppleSubError> subErrors;

    public BaseException() {
    }

    public BaseException(AppleMainError mainError) {
        this.code = mainError.getCode();
        this.message = mainError.getMessage();
        this.solution = mainError.getSolution();
        if (mainError.getSubErrors() != null && mainError.getSubErrors().size() > 0) {
            this.subErrors = mainError.getSubErrors();
        }
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

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public List<AppleSubError> getSubErrors() {
        return subErrors;
    }

    public void setSubErrors(List<AppleSubError> subErrors) {
        this.subErrors = subErrors;
    }

    public void addSubError(AppleSubError subError){
        if (subErrors == null) {
            subErrors = new ArrayList<AppleSubError>();
        }
        subErrors.add(subError);
    }

    protected AppleMainError getInvalidArgumentsError(Locale locale) {
        return AppleMainErrors.getError(AppleMainErrorType.INVALID_ARGUMENTS, locale);
    }

    protected void setMainError(AppleMainError mainError) {
        setCode(mainError.getCode());
        setMessage(mainError.getMessage());
        setSolution(mainError.getSolution());
    }

    /**
     * 对服务名进行标准化处理：如book.upload转换为book-upload，
     *
     * @param method
     * @return
     */
    protected String transform(String serviceName) {
        if(serviceName != null){
        	serviceName = serviceName.replace(".", "-");
            return serviceName;
        }else{
            return "LACK_SERVICE";
        }
    }
    
    @SuppressWarnings("rawtypes")
	public String getInterfaceName(Class clazz) {
		Class[] clazzs = clazz.getInterfaces();
		if(clazzs.length > 0) {
			return clazzs[0].getName();
		}
		else {
			return clazz.getName();
		}
	}

}

