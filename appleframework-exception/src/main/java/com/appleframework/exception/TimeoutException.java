/**
 * 版权声明： 版权所有 违者必究 2012
 * 日    期：12-6-5
 */
package com.appleframework.exception;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.appleframework.exception.error.AppleMainError;
import com.appleframework.exception.error.AppleSubError;
import com.appleframework.exception.error.AppleSubErrorType;
import com.appleframework.exception.error.AppleSubErrors;

import java.util.ArrayList;
import java.util.Locale;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author 徐少敏
 * @version 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "error")
public class TimeoutException extends AppleException {

	private static final long serialVersionUID = -4131567927036108840L;

	private static final String RSP = "rsp.";

    private static final String SERVICE_TIMEOUT = "-timeout";

    public TimeoutException() {
    }

    public TimeoutException(String method, Locale locale, int timeout) {
    	if(null == locale)
    		locale = Locale.CHINA;
        AppleMainError mainError = AppleSubErrors.getMainError(AppleSubErrorType.RSP_SERVICE_TIMEOUT, locale);
        ArrayList<AppleSubError> subErrors = new ArrayList<AppleSubError>();

        String errorCodeKey = RSP + transform(method) + SERVICE_TIMEOUT;
        AppleSubError subError = AppleSubErrors.getSubError(errorCodeKey,
                AppleSubErrorType.RSP_SERVICE_TIMEOUT.value(),
                locale,
                method, timeout);
        subErrors.add(subError);

        setSubErrors(subErrors);
        setMainError(mainError);
    }

}

