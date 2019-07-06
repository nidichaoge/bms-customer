package com.mouse.bms.customer.dal.dataobject;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : CustomerDO
 * @date : 2019/3/3 21:19
 * @description : customer entity
 */
@Data
@Accessors(chain = true)
public class CustomerDO {

    private Long id;

    private Long businessId;

    private String nickName;

    private String mobile;

    private Short gender;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    private String description;

}
