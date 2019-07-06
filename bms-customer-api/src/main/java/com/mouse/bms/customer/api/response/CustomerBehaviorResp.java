package com.mouse.bms.customer.api.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : CustomerBehaviorResp
 * @date : 2019/3/2 14:59
 * @description :
 */
@Data
@Accessors(chain = true)
public class CustomerBehaviorResp implements Serializable {

    private static final long serialVersionUID = 1912003666341123389L;

    private Integer tradeCount;

    private Double tradeAmount;

    private Integer refundCount;

    private Double refundAmount;

    private LocalDateTime lastTradeTime;

    private Double realPrice;

}
