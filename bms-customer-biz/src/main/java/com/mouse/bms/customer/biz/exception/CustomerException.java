package com.mouse.bms.customer.biz.exception;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : CustomerException
 * @date : 2019/3/19 17:09
 * @description :
 */
public class CustomerException extends Exception {

    public CustomerException(String message) {
        super(message);
    }

    public CustomerException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerException(Throwable cause) {
        super(cause);
    }

    public CustomerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
