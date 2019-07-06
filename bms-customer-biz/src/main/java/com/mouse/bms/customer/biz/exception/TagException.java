package com.mouse.bms.customer.biz.exception;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : TagException
 * @date : 2019/3/18 17:19
 * @description :
 */
public class TagException extends Exception {

    public TagException(String message) {
        super(message);
    }

    public TagException(String message, Throwable cause) {
        super(message, cause);
    }

    public TagException(Throwable cause) {
        super(cause);
    }

    public TagException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
