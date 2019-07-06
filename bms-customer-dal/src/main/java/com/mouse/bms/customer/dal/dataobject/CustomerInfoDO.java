package com.mouse.bms.customer.dal.dataobject;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : CustomerInfoDO
 * @date : 2019/3/3 21:20
 * @description : customerInfo entity
 */
@Data
@Accessors(chain = true)
public class CustomerInfoDO {

    private Long id;

    private Long businessId;

    private Long customerId;

    private String weChat;

    private String area;

    private LocalDateTime birthday;

    private LocalDateTime anniversary;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

}
