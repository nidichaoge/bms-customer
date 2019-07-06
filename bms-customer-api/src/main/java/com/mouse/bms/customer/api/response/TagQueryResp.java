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
 * @fileName : TagQueryResp
 * @date : 2019/3/2 15:23
 * @description :
 */
@Data
@Accessors(chain = true)
public class TagQueryResp implements Serializable {

    private static final long serialVersionUID = -5803287712476356704L;

    private Long tagId;

    private String name;

    private Long customerNum;

    private LocalDateTime createdAt;

    private String description;

}
