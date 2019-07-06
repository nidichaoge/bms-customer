package com.mouse.bms.customer.api.request;

import java.io.Serializable;
import java.util.Calendar;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : TagEditRequest
 * @date : 2019/3/18 11:01
 * @description :
 */
@Data
@Accessors(chain = true)
public class TagEditRequest implements Serializable {

    private static final long serialVersionUID = 549587422862344637L;

    private Long businessId;

    private Long tagId;

    private String name;

    private Boolean auto;

    private String description;

}
