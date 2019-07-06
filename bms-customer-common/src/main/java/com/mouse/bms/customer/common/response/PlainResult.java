package com.mouse.bms.customer.common.response;

import lombok.Data;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : PlainResult
 * @date : 2019/3/2 13:40
 * @description : 接口返回是类
 */
@Data
public class PlainResult<T> extends BaseResult {

    private static final long serialVersionUID = 497215634248063731L;
    /**
     * 接口返回的数据
     */
    private T data;

}
