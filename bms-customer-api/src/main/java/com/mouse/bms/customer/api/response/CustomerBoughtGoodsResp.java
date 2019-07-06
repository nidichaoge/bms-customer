package com.mouse.bms.customer.api.response;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : CustomerBoughtGoodsResp
 * @date : 2019/3/2 15:05
 * @description :
 */
@Data
@Accessors(chain = true)
public class CustomerBoughtGoodsResp implements Serializable {

    private static final long serialVersionUID = -202764245581509050L;

    private Long productId;

    private String name;

    private Integer price;

    private Integer realPrice;

    private String image;

}
