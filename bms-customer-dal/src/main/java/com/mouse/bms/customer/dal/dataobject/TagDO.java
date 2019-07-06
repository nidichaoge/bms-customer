package com.mouse.bms.customer.dal.dataobject;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : TagDO
 * @date : 2019/3/2 15:54
 * @description : tag entity
 */
@Data
@Accessors(chain = true)
public class TagDO {

    private Long id;

    private Long businessId;

    private String name;

    private Long customerNum;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    private Boolean auto;

    private String description;

}
