package com.mouse.bms.customer.common.enums;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : CommonResultEnum
 * @date : 2019/3/2 13:28
 * @description : 接口通用返回码
 */
public enum CommonResultEnum {

    SUCCESS(200, "success"),
    EXCEPTION(-100, "server exception, msg is %s"),
    ILLEGAL_PARAM(-101, "illegal parameter, param is %s"),
    ILLEGAL_PARAM_LENGTH(-102, "illegal paramter length, param is %s"),
    ILLEGAL_AUTH(-103, "illegal auth"),
    ILLEGAL_AUTH_STATUS(-104, "illegal auth status, current status is %s"),
    ERROR_DB(-105, "db error"),
    ERROR_INVOKE_PROXY(-106, "invoke proxy error, proxy result code is %s, msg is %s"),
    ERROR_INVOKE_MESSAGECENTER(-109, "invoke messagecenter error, messagecenter result code is %s, msg is %s"),
    ERROR_DATA_NOT_EXISTS(-107, "%s data not exist"),
    ERROR_DATA_EXISTS(-108, "%s data exist"),
    DUBBO_RPC_ERROR(-109, "Dubbo rpc error,msg is %s"),
    QUOTA_FULL(-110, "quota full, quota is %s"),
    ERROR_BID(-111, "bid error, msg is %s");

    public final int code;
    public final String message;

    CommonResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
