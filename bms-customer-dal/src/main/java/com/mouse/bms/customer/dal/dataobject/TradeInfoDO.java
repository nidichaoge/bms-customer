package com.mouse.bms.customer.dal.dataobject;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : TradeInfoDO
 * @date : 2019/3/3 21:23
 * @description : tradeInfo entity
 */
@Data
@Accessors(chain = true)
public class TradeInfoDO {

    private Long id;

    private Long businessId;

    private Long customerId;

    private Integer tradeCount;

    private Double totalAmount;

    private LocalDateTime lastTradeAt;

    private Integer version;

}
