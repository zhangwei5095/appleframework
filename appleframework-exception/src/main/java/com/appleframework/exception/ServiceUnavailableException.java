/**
 *
 * 日    期：12-2-23
 */
package com.appleframework.exception;


import com.appleframework.exception.error.AppleMainError;
import com.appleframework.exception.error.AppleSubError;
import com.appleframework.exception.error.AppleSubErrorType;
import com.appleframework.exception.error.AppleSubErrors;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
@SuppressWarnings("rawtypes")
public class ServiceUnavailableException extends BaseException {

	private static final long serialVersionUID = -3259477976501742491L;

	private static final String RSP = "rsp.";

    private static final String SERVICE_UNAVAILABLE = "-unavailable";

    //注意，这个不能删除，否则无法进行流化
    public ServiceUnavailableException() {
    }

    public ServiceUnavailableException(Class clazz, Locale locale) {
    	if(null == locale)
    		locale = Locale.CHINA;
        AppleMainError mainError = AppleSubErrors.getMainError(AppleSubErrorType.RSP_SERVICE_UNAVAILABLE, locale);
        String serviceName = getInterfaceName(clazz);
        String errorCodeKey = RSP + transform(serviceName) + SERVICE_UNAVAILABLE;
        AppleSubError subError = AppleSubErrors.getSubError(errorCodeKey,
                AppleSubErrorType.RSP_SERVICE_UNAVAILABLE.value(),
                locale, serviceName,"NONE","NONE");
        ArrayList<AppleSubError> subErrors = new ArrayList<AppleSubError>();
        subErrors.add(subError);

        setMainError(mainError);
        setSubErrors(subErrors);
    }
    
    public ServiceUnavailableException(Class clazz) {
        AppleMainError mainError = AppleSubErrors.getMainError(AppleSubErrorType.RSP_SERVICE_UNAVAILABLE);
        String serviceName = getInterfaceName(clazz);
        String errorCodeKey = RSP + transform(serviceName) + SERVICE_UNAVAILABLE;
        AppleSubError subError = AppleSubErrors.getSubError(errorCodeKey,
                AppleSubErrorType.RSP_SERVICE_UNAVAILABLE.value(),
                Locale.CHINA, serviceName,"NONE","NONE");
        ArrayList<AppleSubError> subErrors = new ArrayList<AppleSubError>();
        subErrors.add(subError);

        setMainError(mainError);
        setSubErrors(subErrors);
    }

    public ServiceUnavailableException(Class clazz, Locale locale, Throwable throwable) {
    	if(null == locale)
    		locale = Locale.CHINA;
        AppleMainError mainError = AppleSubErrors.getMainError(AppleSubErrorType.RSP_SERVICE_UNAVAILABLE, locale);
        ArrayList<AppleSubError> subErrors = new ArrayList<AppleSubError>();
        String serviceName = getInterfaceName(clazz);
        String errorCodeKey = RSP + transform(serviceName) + SERVICE_UNAVAILABLE;
        Throwable srcThrowable = throwable;
        if(throwable.getCause() != null){
            srcThrowable = throwable.getCause();
        }
        AppleSubError subError = AppleSubErrors.getSubError(errorCodeKey,
                AppleSubErrorType.RSP_SERVICE_UNAVAILABLE.value(),
                locale,
                serviceName, srcThrowable.getClass().getName(),getThrowableInfo(throwable));
        subErrors.add(subError);

        setSubErrors(subErrors);
        setMainError(mainError);
    }
    
    private String getThrowableInfo(Throwable throwable) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(1024);
        PrintStream printStream = new PrintStream(outputStream);
        throwable.printStackTrace(printStream);
        return outputStream.toString();
    }
}

