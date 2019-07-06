package com.mouse.bms.customer.api.request;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : CustomerInfoRequest
 * @date : 2019/3/2 14:41
 * @description :
 */
@Data
@Accessors(chain = true)
public class CustomerAddRequest implements Serializable {

    private static final long serialVersionUID = 3235282584736634678L;

    private Long businessId;

    private String nickName;

    private String mobile;

    private Short gender;

    private String description;

    private CustomerDetailInfo customerDetailInfo;

}
