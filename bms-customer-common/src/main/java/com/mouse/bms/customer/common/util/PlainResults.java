package com.mouse.bms.customer.common.util;

import com.mouse.bms.customer.common.response.PlainResult;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : PlainResults
 * @date : 2019/3/18 10:43
 * @description :
 */
public class PlainResults {

    static int SUCCESS = 200;

    public static <T> PlainResult<T> success(T data) {
        return success(data, null);
    }

    public static <T> PlainResult<T> success(T data, String message) {
        return success(data, SUCCESS, message);
    }

    public static <T> PlainResult<T> success(T data, int code, String message) {
        return get(true, data, message, code);
    }


    public static <T> PlainResult<T> error(int code, String msg) {
        return error(null, code, msg);
    }

    public static <T> PlainResult<T> error(T data, int code, String msg) {
        return get(false, data, msg, code);
    }


    public static <T> PlainResult<T> get(boolean success, T data, String msg, int code) {
        if (!success && code == SUCCESS) {
            throw new IllegalArgumentException(
                "PlainResults.error() method's code must not be " + SUCCESS);
        }

        PlainResult<T> result = new PlainResult<>();
        result.setSuccess(success);
        result.setData(data);
        result.setMessage(msg == null ? "" : msg);
        result.setCode(code);
        return result;
    }

}
