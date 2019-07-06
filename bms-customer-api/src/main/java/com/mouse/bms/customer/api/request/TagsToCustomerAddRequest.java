package com.mouse.bms.customer.api.request;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : TagsToCustomerAddRequest
 * @date : 2019/3/28 15:17
 * @description :
 */
@Data
@Accessors(chain = true)
public class TagsToCustomerAddRequest implements Serializable {

    private static final long serialVersionUID = -2556102024861616105L;

    private Long businessId;

    private Long customerId;

    private List<Long> tagIds;

    private Short tagSource;

}
