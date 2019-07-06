package com.mouse.bms.customer.api.request;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : CustomerDetailInfo
 * @date : 2019/3/18 16:04
 * @description :
 */
@Data
@Accessors(chain = true)
public class CustomerDetailInfo implements Serializable {

    private static final long serialVersionUID = 1195928689514857991L;

    private String weChat;

    private LocalDateTime birthday;

    private LocalDateTime anniversary;

    private String area;

}
