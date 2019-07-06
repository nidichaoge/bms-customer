package com.mouse.bms.customer.api.response;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : CustomerTagsResp
 * @date : 2019/3/2 14:50
 * @description :
 */
@Data
@Accessors(chain = true)
public class CustomerTagsResp implements Serializable {

    private static final long serialVersionUID = 4954314652705151756L;

    private Long customerId;

    private List<String> tagNames;

}
