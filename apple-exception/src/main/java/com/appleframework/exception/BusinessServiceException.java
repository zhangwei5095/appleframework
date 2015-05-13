/**
 *
 * 日    期：12-2-23
 */
package com.appleframework.exception;

import com.appleframework.exception.error.AppleMainError;
import com.appleframework.exception.error.AppleMainErrorType;
import com.appleframework.exception.error.AppleMainErrors;
import com.appleframework.exception.error.AppleSubError;
import com.appleframework.exception.error.AppleSubErrors;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Locale;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author 陈雄华
 * @version 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "error")
public class BusinessServiceException extends AppleException {

	private static final long serialVersionUID = 1500030976495988681L;

	private static final String RSP = "rsp.";

    private static final String SERVICE_ERROR = "-error:";

    //注意，这个不能删除，否则无法进行流化
    public BusinessServiceException() {
    }


    /**
     * 服务发生错误的错误响应，错误码的格式为：rsv.***-service-error:###,假设
     * serviceName为book.upload，error_code为INVLIAD_USERNAME_OR_PASSWORD，则错误码会被格式化为：
     * rsv.book-upload-service-error:INVLIAD_USERNAME_OR_PASSWORD
     *
     * @param serviceName 服务名，如book.upload,会被自动转换为book-upload
     * @param errorCode   错误的代码，如INVLIAD_USERNAME_OR_PASSWORD,在错误码的后面，一般为大写或数字。
     * @param locale      本地化对象
     * @param params      错误信息的参数，如错误消息的值为this is a {0} error，则传入的参数为big时，错误消息格式化为：
     *                    this is a big error
     */
    @Deprecated
    public BusinessServiceException(String serviceName, String errorCode, Locale locale, Object... params) {
    	if(null == locale)
    		locale = Locale.CHINA;
        AppleMainError mainError = AppleMainErrors.getError(AppleMainErrorType.BUSINESS_LOGIC_ERROR,locale);
        serviceName = transform(serviceName);
        String subErrorCode = RSP + serviceName + SERVICE_ERROR + errorCode;
        AppleSubError subError = AppleSubErrors.getSubError(subErrorCode, subErrorCode, locale, params);
        ArrayList<AppleSubError> subErrors = new ArrayList<AppleSubError>();
        subErrors.add(subError);

        setMainError(mainError);
        setSubErrors(subErrors);
    }

    /**
     * 服务发生错误的错误响应，错误码的格式为：rsv.***-service-error:###,假设
     * serviceName为file.upload，error_code为INVLIAD_USERNAME_OR_PASSWORD，则错误码会被格式化为：
     * rsv.file-upload-service-error:INVLIAD_USERNAME_OR_PASSWORD
     *
     * @param context     请求上下文
     * @param errorCode   错误的代码，如INVLIAD_USERNAME_OR_PASSWORD,在错误码的后面，一般为大写或数字。
     * @param params      错误信息的参数，如错误消息的值为this is a {0} error，则传入的参数为big时，错误消息格式化为：
     *                    this is a big error
     */
    @SuppressWarnings("rawtypes")
	public BusinessServiceException(Class clazz, String errorCode, Locale locale, Object... params) {
    	if(null == locale)
    		locale = Locale.CHINA;
    	String serviceName = transform(getInterfaceName(clazz));
        AppleMainError mainError = AppleMainErrors.getError(AppleMainErrorType.BUSINESS_LOGIC_ERROR, locale, serviceName);
        
        String subErrorCode = RSP + serviceName + SERVICE_ERROR + errorCode;
        AppleSubError subError = AppleSubErrors.getSubError(subErrorCode, subErrorCode, locale, params);
        ArrayList<AppleSubError> subErrors = new ArrayList<AppleSubError>();
        subErrors.add(subError);

        setMainError(mainError);
        setSubErrors(subErrors);
    }
    
    /**
     * 服务发生错误的错误响应，错误码的格式为：rsv.***-service-error:###,假设
     * serviceName为file.upload，error_code为INVLIAD_USERNAME_OR_PASSWORD，则错误码会被格式化为：
     * rsv.file-upload-service-error:INVLIAD_USERNAME_OR_PASSWORD
     *
     * @param context     请求上下文
     * @param errorCode   错误的代码，如INVLIAD_USERNAME_OR_PASSWORD,在错误码的后面，一般为大写或数字。
     * @param params      错误信息的参数，如错误消息的值为this is a {0} error，则传入的参数为big时，错误消息格式化为：
     *                    this is a big error
     */
    @SuppressWarnings("rawtypes")
	public BusinessServiceException(Class clazz, String errorCode, Object... params) {
    	Locale locale = Locale.CHINA;
    	String serviceName = transform(getInterfaceName(clazz));
        AppleMainError mainError = AppleMainErrors.getError(AppleMainErrorType.BUSINESS_LOGIC_ERROR, locale, serviceName);
        
        String subErrorCode = RSP + serviceName + SERVICE_ERROR + errorCode;
        AppleSubError subError = AppleSubErrors.getSubError(subErrorCode, subErrorCode, locale, params);
        ArrayList<AppleSubError> subErrors = new ArrayList<AppleSubError>();
        subErrors.add(subError);

        setMainError(mainError);
        setSubErrors(subErrors);
    }

}

