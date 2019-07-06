package com.mouse.bms.customer.dal.dataobject;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : RefundInfoDO
 * @date : 2019/3/3 21:24
 * @description : refundInfo entity
 */
@Data
@Accessors(chain = true)
public class RefundInfoDO {

    private Long id;

    private Long businessId;

    private Long customerId;

    private Integer refundCount;

    private Double totalRefundAmount;

    private LocalDateTime lastRefundAt;

    private Integer version;

}
