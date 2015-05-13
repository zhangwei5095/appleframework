/**
 *
 * 日    期：12-2-11
 */
package com.appleframework.exception.error;

import java.util.List;

/**
 * <pre>
 * 功能说明：
 * </pre>
 * 
 * @author 徐少敏
 * @version 1.0
 */
public interface AppleMainError {

	String getCode();

	String getMessage();

	String getSolution();

	List<AppleSubError> getSubErrors();

	AppleMainError addSubError(AppleSubError subError);

}
