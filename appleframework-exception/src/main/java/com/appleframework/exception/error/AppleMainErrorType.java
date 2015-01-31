/**
 *
 * 日    期：12-2-23
 */
package com.appleframework.exception.error;

import java.util.EnumMap;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author 徐少敏
 * @version 1.0
 */
public enum AppleMainErrorType {

    SERVICE_CURRENTLY_UNAVAILABLE,
    INSUFFICIENT_RSV_PERMISSIONS,

    BUSINESS_LOGIC_ERROR,
    MISSING_REQUIRED_ARGUMENTS,
    INVALID_ARGUMENTS;

    private static EnumMap<AppleMainErrorType, String> errorCodeMap = new EnumMap<AppleMainErrorType, String>(AppleMainErrorType.class);

    static {
        errorCodeMap.put(AppleMainErrorType.SERVICE_CURRENTLY_UNAVAILABLE, "1");
        errorCodeMap.put(AppleMainErrorType.INSUFFICIENT_RSV_PERMISSIONS, "2");
        errorCodeMap.put(AppleMainErrorType.BUSINESS_LOGIC_ERROR, "9");
        errorCodeMap.put(AppleMainErrorType.MISSING_REQUIRED_ARGUMENTS, "32");
        errorCodeMap.put(AppleMainErrorType.INVALID_ARGUMENTS, "33");
    }

    public String value() {
        return errorCodeMap.get(this);
    }
}

