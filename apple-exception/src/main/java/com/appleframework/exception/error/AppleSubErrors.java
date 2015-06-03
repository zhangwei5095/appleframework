/**
 *
 * 日    期：12-2-11
 */
package com.appleframework.exception.error;

import org.apache.log4j.Logger;
import org.springframework.context.NoSuchMessageException;

import com.appleframework.exception.utils.MessageSourceUtility;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.Locale;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author 徐少敏
 * @version 1.0
 */
public class AppleSubErrors implements Serializable {

	private static final long serialVersionUID = 7566195425214797570L;

	protected static Logger logger = Logger.getLogger(AppleSubErrors.class.getName());

    //子错误和主错误对应Map,key为子错误代码，值为主错误代码
    private static final EnumMap<AppleSubErrorType, AppleMainErrorType> SUBERROR_MAINERROR_MAPPINGS =
            new EnumMap<AppleSubErrorType, AppleMainErrorType>(AppleSubErrorType.class);

    static {
        SUBERROR_MAINERROR_MAPPINGS.put(
                AppleSubErrorType.RSP_SERVICE_UNAVAILABLE, AppleMainErrorType.SERVICE_CURRENTLY_UNAVAILABLE);
        SUBERROR_MAINERROR_MAPPINGS.put(
                AppleSubErrorType.RSP_SERVICE_TIMEOUT, AppleMainErrorType.SERVICE_CURRENTLY_UNAVAILABLE);
        SUBERROR_MAINERROR_MAPPINGS.put(
                AppleSubErrorType.RSV_MISSING_PARAMETER, AppleMainErrorType.MISSING_REQUIRED_ARGUMENTS);
        SUBERROR_MAINERROR_MAPPINGS.put(
                AppleSubErrorType.RSV_PARAMETERS_MISMATCH, AppleMainErrorType.INVALID_ARGUMENTS);
        SUBERROR_MAINERROR_MAPPINGS.put(
                AppleSubErrorType.RSV_INVALID_PARAMETE, AppleMainErrorType.INVALID_ARGUMENTS);
        SUBERROR_MAINERROR_MAPPINGS.put(
                AppleSubErrorType.RSV_NOT_EXIST, AppleMainErrorType.INVALID_ARGUMENTS);
        SUBERROR_MAINERROR_MAPPINGS.put(
                AppleSubErrorType.RSV_INVALID_PERMISSION, AppleMainErrorType.INSUFFICIENT_RSV_PERMISSIONS);
    }

    private static final String PARAM_1 = "xxx";
    private static final String PARAM_2 = "yyy";

    /**
     * 获取对应子错误的主错误
     *
     * @param subErrorType
     * @param locale
     * @return
     */
    public static AppleMainError getMainError(AppleSubErrorType subErrorType, Locale locale, Object... params) {
        return AppleMainErrors.getError(SUBERROR_MAINERROR_MAPPINGS.get(subErrorType), locale,params);
    }
    
    /**
     * 获取对应子错误的主错误
     *
     * @param subErrorType
     * @param locale
     * @return
     */
    public static AppleMainError getMainError(AppleSubErrorType subErrorType) {
        return AppleMainErrors.getError(SUBERROR_MAINERROR_MAPPINGS.get(subErrorType));
    }


    /**
     * @param subErrorCode 子错误代码
     * @param subErrorKey  子错误信息键
     * @param locale       本地化
     * @param params       本地化消息参数
     * @return
     */
    public static AppleSubError getSubError(String subErrorCode, String subErrorKey, Locale locale, Object... params) {
        try {
            String parsedSubErrorMessage = MessageSourceUtility.getMessageSourceAccessor()
            		.getMessage(subErrorKey, params, locale);
            return new AppleSubError(subErrorCode, parsedSubErrorMessage);
        } catch (NoSuchMessageException e) {
            logger.error("不存在对应的错误键：{" + subErrorCode + "}，请检查是否正确配置了应用的错误资源，" +
                    "默认位置：i18n/service/serviceError");
            throw e;
        }
    }
    
    /**
     * @param subErrorCode 子错误代码
     * @param subErrorKey  子错误信息键
     * @param locale       本地化
     * @param params       本地化消息参数
     * @return
     */
    public static AppleSubError getSubError(String subErrorCode) {
    	return new AppleSubError(subErrorCode);
    }

    public static String getSubErrorCode(AppleSubErrorType subErrorType, Object... params) {
        String subErrorCode = subErrorType.value();
        if (params.length > 0) {
            if (params.length == 1) {
                subErrorCode = subErrorCode.replace(PARAM_1, (String) params[0]);
            } else {
                subErrorCode = subErrorCode.replace(PARAM_1, (String) params[0]);
                if (params[1] != null) {
                    subErrorCode = subErrorCode.replace(PARAM_2, (String) params[1]);
                }
            }
        }
        return subErrorCode;
    }
}

