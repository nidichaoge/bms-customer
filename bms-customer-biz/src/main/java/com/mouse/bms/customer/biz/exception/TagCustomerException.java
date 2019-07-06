package com.mouse.bms.customer.biz.exception;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : TagCustomerException
 * @date : 2019/3/28 14:27
 * @description :
 */
public class TagCustomerException extends Exception {

    public TagCustomerException(String message) {
        super(message);
    }

    public TagCustomerException(String message, Throwable cause) {
        super(message, cause);
    }

    public TagCustomerException(Throwable cause) {
        super(cause);
    }

    public TagCustomerException(String message, Throwable cause, boolean enableSuppression,
                                boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
