package com.mouse.bms.customer.common.response;

import com.mouse.bms.customer.common.enums.CommonResultEnum;

import java.io.Serializable;

import lombok.Data;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : BaseResult
 * @date : 2019/3/2 13:20
 * @description : 接口返回的基类
 */
@Data
public class BaseResult implements Serializable {

    private static final long serialVersionUID = -2865367251141794492L;

    public BaseResult() {
        this.success = true;
        this.code = CommonResultEnum.SUCCESS.code;
        this.message = CommonResultEnum.SUCCESS.message;
    }

    /**
     * 标识本次调用是否返回
     */
    private boolean success;

    /**
     * 本次调用返回code，一般为错误类型(非200)
     */
    private int code;

    /**
     * 本次调用返回的消息，一般为错误消息
     */
    private String message;

    /**
     * 标识本次请求ID
     */
    private String requestId;

    /**
     * 设置错误信息
     */
    public <R extends BaseResult> R setErrorMessage(int code, String message) {
        this.code = code;
        this.success = false;
        this.message = message;
        return (R) this;
    }

}
