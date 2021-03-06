package com.appleframework.context.message;

import org.apache.log4j.Logger;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.util.Assert;

import com.appleframework.context.utils.MessageSourceUtility;
import com.appleframework.exception.AppleException;

import java.util.Locale;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author 徐少敏
 * @version 1.0
 */
public class MessageManager {

	protected static Logger logger = Logger.getLogger(MessageManager.class.getName());
	
	private static MessageSourceAccessor messageSourceAccessor = MessageSourceUtility.getMessageSourceAccessor();
	
    public static String getExceptionMessage(AppleException appleException) {
    	Locale locale = Locale.CHINA;
    	String key = appleException.getKey();
        try {
            Assert.notNull(messageSourceAccessor, "请先设置错误消息的国际化资源");
            return messageSourceAccessor.getMessage(key, appleException.getParams(), locale);
        } catch (NoSuchMessageException e) {
            logger.error("不存在对应的错误键：{" + key + "}，请检查是否在i18n/service/error的错误资源");
            throw e;
        }
    }
    
    public static String getMessage(String code, Locale locale, Object[] params) {
    	try {
    		Assert.notNull(MessageSourceUtility.getMessageSourceAccessor(), "请先设置错误消息的国际化资源");
    		return MessageSourceUtility.getMessageSourceAccessor().getMessage(code, params, locale);
        } catch (NoSuchMessageException e) {
        	logger.error("不存在对应的错误键：{" + code + "}，请检查是否在i18n/service/error的错误资源");
        	throw e;
        }
	}
    
    public static String getMessage(String code, Object[] params) {
    	Locale locale = Locale.CHINA;
        try {
        	Assert.notNull(MessageSourceUtility.getMessageSourceAccessor(), "请先设置错误消息的国际化资源");
        	return MessageSourceUtility.getMessageSourceAccessor().getMessage(code, params, locale);
        } catch (NoSuchMessageException e) {
        	logger.error("不存在对应的错误键：{" + code + "}，请检查是否在i18n/service/error的错误资源");
        	throw e;
        }
	}

}

