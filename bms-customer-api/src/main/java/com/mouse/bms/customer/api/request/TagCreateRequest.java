package com.mouse.bms.customer.api.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : TagCreateRequest
 * @date : 2019/3/18 10:32
 * @description :
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "标签创建对象",description = "标签创建对象的")
public class TagCreateRequest implements Serializable {

    private static final long serialVersionUID = -2414753737042237749L;

    @ApiModelProperty(value = "店铺id",name = "businessId",required = true,example = "1L")
    private Long businessId;

    private String name;

    private Boolean auto;

    private String description;

}
