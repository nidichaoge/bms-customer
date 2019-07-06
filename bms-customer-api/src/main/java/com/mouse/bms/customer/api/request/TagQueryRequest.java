package com.mouse.bms.customer.api.request;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : TagQueryRequest
 * @date : 2019/3/2 15:19
 * @description :
 */
@Data
@Accessors(chain = true)
public class TagQueryRequest implements Serializable {

    private static final long serialVersionUID = -8259824977027368273L;

    private Long businessId;

    private String keyword;

    private Integer page;

    private Integer pageSize;

    private String order;

    private String orderBy;

    private Boolean auto;

}
