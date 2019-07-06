package com.mouse.bms.customer.api.request;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : CustomerEditCustomer
 * @date : 2019/3/18 16:10
 * @description :
 */
@Data
@Accessors(chain = true)
public class CustomerEditRequest implements Serializable {

    private static final long serialVersionUID = -7814760567236785717L;

    private Long businessId;

    private Long customerId;

    private String nickName;

    private String mobile;

    private Short gender;

    private String description;

    private CustomerDetailInfo customerDetailInfo;

}
