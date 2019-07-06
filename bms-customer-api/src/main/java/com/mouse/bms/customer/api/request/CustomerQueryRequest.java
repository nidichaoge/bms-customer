package com.mouse.bms.customer.api.request;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : CustomerSearchRequest
 * @date : 2019/3/2 14:16
 * @description :
 */
@Data
@Accessors(chain = true)
public class CustomerQueryRequest implements Serializable {

    private static final long serialVersionUID = -701735683873050732L;

    /**
     * 店铺ID
     */
    private Long businessId;

    /**
     * 页码
     */
    private Integer page;

    /**
     * 每页返回几条
     */
    private Integer pageSize;

    /**
     * 根据keyword对客户手机号模糊查询
     */
    private String keyword;

    /**
     * 排序规则
     */
    private String order;

    /**
     * 排序
     */
    private String orderBy;

}
