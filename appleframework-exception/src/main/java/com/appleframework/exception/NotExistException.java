/**
 *
 * 日    期：12-2-23
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
 * @author 陈雄华
 * @version 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "error")
public class NotExistException extends BaseException {

	private static final long serialVersionUID = -2467323337391884616L;
	
	public static final String RSV = "rsv.";
    public static final String NOT_EXIST_INVALID = "-not-exist:invalid-";

    //注意，这个不能删除，否则无法进行流化
    public NotExistException() {
    }

    /**
     * 对象不存在的错误对象。当根据<code>queryFieldName</code>查询<code>objectName</code>时，查不到记录，则返回该错误对象。
     *
     * @param objectName     对象的名称
     * @param queryFieldName 查询字段的名称
     * @param locale         本地化对象
     * @param params         错误信息的参数，如错误消息的值为:use '{0}'({1})can't find '{2}' object ，则传入的参数为001时，错误消息格式化为：
     *                       can't find user by 001
     */
    public NotExistException(String objectName, String queryFieldName, Object queryFieldValue, Locale locale) {
    	if(null == locale)
    		locale = Locale.CHINA;
        AppleMainError mainError = AppleSubErrors.getMainError(AppleSubErrorType.RSV_NOT_EXIST, locale);
        String subErrorCode = AppleSubErrors.getSubErrorCode(AppleSubErrorType.RSV_NOT_EXIST, objectName, queryFieldName);

        AppleSubError subError = AppleSubErrors.getSubError(subErrorCode, AppleSubErrorType.RSV_NOT_EXIST.value(), locale,
                                                 queryFieldName, queryFieldValue,objectName);
        ArrayList<AppleSubError> subErrors = new ArrayList<AppleSubError>();
        subErrors.add(subError);

        setMainError(mainError);
        setSubErrors(subErrors);
    }
}

