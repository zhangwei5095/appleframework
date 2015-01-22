package com.appleframework.exception;

import java.util.EnumMap;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author 徐少敏
 * @version 1.0
 */
public enum ServiceErrorType {
	
    RSP_SERVICE_UNAVAILABLE,
    RSP_SERVICE_TIMEOUT,

    RSV_NOT_EXIST,
    RSV_INVALID_PERMISSION,
    RSV_MISSING_PARAMETER,
    RSV_INVALID_PARAMETE,
    RSV_PARAMETERS_MISMATCH;

    private static EnumMap<ServiceErrorType, String> errorKeyMap = new EnumMap<ServiceErrorType, String>(ServiceErrorType.class);

    static {
        errorKeyMap.put(ServiceErrorType.RSP_SERVICE_UNAVAILABLE, "rsp.xxx-service-unavailable");
        errorKeyMap.put(ServiceErrorType.RSP_SERVICE_TIMEOUT, "rsp.xxx-service-timeout");

        errorKeyMap.put(ServiceErrorType.RSV_NOT_EXIST, "rsv.xxx-not-exist:invalid-yyy");
        errorKeyMap.put(ServiceErrorType.RSV_MISSING_PARAMETER, "rsv.missing-parameter:xxx");
        errorKeyMap.put(ServiceErrorType.RSV_INVALID_PARAMETE, "rsv.invalid-paramete:xxx");
        errorKeyMap.put(ServiceErrorType.RSV_INVALID_PERMISSION, "rsv.invalid-permission");
        errorKeyMap.put(ServiceErrorType.RSV_PARAMETERS_MISMATCH, "rsv.parameters-mismatch:xxx-and-yyy");
    }

    public String value() {
        return errorKeyMap.get(this);
    }
}

