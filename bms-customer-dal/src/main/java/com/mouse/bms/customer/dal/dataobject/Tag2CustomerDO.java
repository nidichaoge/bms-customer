package com.mouse.bms.customer.dal.dataobject;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : Tag2CustomerDO
 * @date : 2019/3/3 21:22
 * @description : tag2Customer entity
 */
@Data
@Accessors(chain = true)
public class Tag2CustomerDO {

    private Long id;

    private Long businessId;

    private Long tagId;

    private Long customerId;

    private Boolean autoTagged;

    private Short tagSource;

    private LocalDateTime createdAt;

    private LocalDateTime deletedAt;

}
