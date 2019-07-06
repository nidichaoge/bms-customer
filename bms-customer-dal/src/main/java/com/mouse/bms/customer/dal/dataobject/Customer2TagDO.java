package com.mouse.bms.customer.dal.dataobject;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : Customer2TagDO
 * @date : 2019/3/3 21:21
 * @description : customer2Tag entity
 */
@Data
@Accessors(chain = true)
public class Customer2TagDO {

    private Long id;

    private Long businessId;

    private Long customerId;

    private Long tagId;

    private Boolean autoTagged;

    private Short tagSource;

    private LocalDateTime createdAt;

    private LocalDateTime deletedAt;

}
