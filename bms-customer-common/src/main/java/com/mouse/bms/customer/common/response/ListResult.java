package com.mouse.bms.customer.common.response;

import java.util.List;

import lombok.Data;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : ListResult
 * @date : 2019/3/2 13:45
 * @description : 接口返回的是list集合
 */
@Data
public class ListResult<T> extends BaseResult {

    private static final long serialVersionUID = 3920501964705031578L;
    /**
     * 接口返回的数据
     */
    private List<T> data;

    /**
     * 返回数据的数量
     */
    private int count;

}
