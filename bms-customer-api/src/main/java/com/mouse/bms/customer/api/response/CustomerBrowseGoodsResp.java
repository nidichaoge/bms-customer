package com.mouse.bms.customer.api.response;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : CustomerBrowseGoodsResp
 * @date : 2019/3/2 15:09
 * @description :
 */
@Data
@Accessors(chain = true)
public class CustomerBrowseGoodsResp implements Serializable {

    private static final long serialVersionUID = -3799396852429352251L;

    private Long productId;

    private String name;

    private String price;

    private String image;

}
