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
 * @fileName : CustomerSearchResp
 * @date : 2019/3/2 14:24
 * @description :
 */
@Data
@Accessors(chain = true)
public class CustomerQueryResp implements Serializable {

    private static final long serialVersionUID = 4433368307368526820L;

    private Long customerId;

    private String nickName;

    private String mobile;

    private Long credit;

    private LocalDateTime createdAt;

    private Integer tradeCount;

    private LocalDateTime lastTradeTime;

    private String description;

}
