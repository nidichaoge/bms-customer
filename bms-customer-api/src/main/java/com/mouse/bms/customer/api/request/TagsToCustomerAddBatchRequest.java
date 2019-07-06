package com.mouse.bms.customer.api.request;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : TagsToCustomerAddBatchRequest
 * @date : 2019/3/28 15:22
 * @description :
 */
@Data
@Accessors(chain = true)
public class TagsToCustomerAddBatchRequest implements Serializable {

    private static final long serialVersionUID = 4788101018042593590L;

    private Long businessId;

    private List<Long> customerIds;

    private Long tagId;

    private Short tagSource;

}
