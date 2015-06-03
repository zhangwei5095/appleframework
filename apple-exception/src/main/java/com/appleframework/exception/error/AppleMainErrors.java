/**
 *
 * 日    期：12-2-11
 */
package com.appleframework.exception.error;

import org.apache.log4j.Logger;
import org.springframework.context.NoSuchMessageException;
import org.springframework.util.Assert;

import com.appleframework.exception.utils.MessageSourceUtility;

import java.io.Serializable;
import java.util.Locale;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author 徐少敏
 * @version 1.0
 */
public class AppleMainErrors implements Serializable {

	private static final long serialVersionUID = 841768290283360292L;

	protected static Logger logger = Logger.getLogger(AppleMainErrors.class.getName());

    private static final String ERROR_CODE_PREFIX = "ERROR_";
    private static final String ERROR_SOLUTION_SUBFIX = "_SOLUTION";
    
    public static AppleMainError getError(AppleMainErrorType mainErrorType, Locale locale, Object... params) {
        String errorMessage = getErrorMessage(ERROR_CODE_PREFIX + mainErrorType.value(),locale,params);
        String errorSolution = getErrorSolution(ERROR_CODE_PREFIX + mainErrorType.value() + ERROR_SOLUTION_SUBFIX, locale);
        return new SimpleMainError(mainErrorType.value(), errorMessage, errorSolution);
    }
    
    public static AppleMainError getError(AppleMainErrorType mainErrorType) {
        return new SimpleMainError(mainErrorType.value());
    }

    private static String getErrorMessage(String code, Locale locale, Object... params) {
        try {
            Assert.notNull(MessageSourceUtility.getMessageSourceAccessor(), "请先设置错误消息的国际化资源");
            return MessageSourceUtility.getMessageSourceAccessor().getMessage(code, params, locale);
        } catch (NoSuchMessageException e) {
            logger.error("不存在对应的错误键：{" + code + "}，请检查是否在i18n/service/error的错误资源");
            throw e;
        }
    }

    private static String getErrorSolution(String code, Locale locale) {
        try {
            Assert.notNull(MessageSourceUtility.getMessageSourceAccessor(), "请先设置错误解决方案的国际化资源");
            return MessageSourceUtility.getMessageSourceAccessor().getMessage(code, new Object[]{}, locale);
        } catch (NoSuchMessageException e) {
            logger.error("不存在对应的错误键：{" + code + "}，请检查是否在i18n/service/error的错误资源");
            throw e;
        }
    }


}

