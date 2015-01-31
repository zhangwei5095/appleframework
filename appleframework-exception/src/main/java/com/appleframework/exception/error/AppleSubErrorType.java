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
public enum AppleSubErrorType {
	
    RSP_SERVICE_UNAVAILABLE,
    RSP_SERVICE_TIMEOUT,

    RSV_NOT_EXIST,
    RSV_INVALID_PERMISSION,
    RSV_MISSING_PARAMETER,
    RSV_INVALID_PARAMETE,
    RSV_PARAMETERS_MISMATCH;

    private static EnumMap<AppleSubErrorType, String> errorKeyMap = new EnumMap<AppleSubErrorType, String>(AppleSubErrorType.class);

    static {
        errorKeyMap.put(AppleSubErrorType.RSP_SERVICE_UNAVAILABLE, "rsp.xxx-unavailable");
        errorKeyMap.put(AppleSubErrorType.RSP_SERVICE_TIMEOUT, "rsp.xxx-timeout");

        errorKeyMap.put(AppleSubErrorType.RSV_NOT_EXIST, "rsv.xxx-not-exist:invalid-yyy");
        errorKeyMap.put(AppleSubErrorType.RSV_MISSING_PARAMETER, "rsv.missing-parameter:xxx");
        errorKeyMap.put(AppleSubErrorType.RSV_INVALID_PARAMETE, "rsv.invalid-paramete:xxx");
        errorKeyMap.put(AppleSubErrorType.RSV_INVALID_PERMISSION, "rsv.invalid-permission");
        errorKeyMap.put(AppleSubErrorType.RSV_PARAMETERS_MISMATCH, "rsv.parameters-mismatch:xxx-and-yyy");
    }

    public String value() {
        return errorKeyMap.get(this);
    }
}

