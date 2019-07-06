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
 * @fileName : CustomerDetailInfoResp
 * @date : 2019/3/4 20:17
 * @description :
 */
@Data
@Accessors(chain = true)
public class CustomerDetailInfoResp implements Serializable {

    private static final long serialVersionUID = 5897979269280255723L;

    private Long businessId;

    private String nickName;

    private String mobile;

    private Short sex;

    private String description;

    private String weChat;

    private LocalDateTime birthday;

    private LocalDateTime anniversary;

    private String area;

    private LocalDateTime createdAt;

}
