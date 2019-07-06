package com.mouse.bms.customer.api.service;

import com.mouse.bms.customer.api.response.CustomerBehaviorResp;
import com.mouse.bms.customer.api.response.CustomerBoughtGoodsResp;
import com.mouse.bms.customer.api.response.CustomerBrowseGoodsResp;
import com.mouse.bms.customer.api.response.CustomerDetailInfoResp;
import com.mouse.bms.customer.api.response.CustomerTagsResp;
import com.mouse.bms.customer.common.response.ListResult;
import com.mouse.bms.customer.common.response.PlainResult;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : CustomerBehaviorService
 * @date : 2019/3/2 14:46
 * @description : 客户行为服务
 */
public interface CustomerBehaviorService {

    /**
     * 查询客户详细信息
     * @param businessId
     * @param customerId
     * @return
     */
    PlainResult<CustomerDetailInfoResp> getCustomerDetailInfo(Long businessId, Long customerId);

    /**
     * 查询客户标签信息
     * @param businessId
     * @param customerId
     * @return
     */
    PlainResult<CustomerTagsResp> getCustomerTags(Long businessId, Long customerId);
    /**
     * 查询客户交易信息
     * @param businessId
     * @param customerId
     * @return
     */
    PlainResult<CustomerBehaviorResp> getCustomerTradeInfo(Long businessId, Long customerId);

    /**
     * 客户购买商品,退款也包含在内
     * @param businessId
     * @param customerId
     * @return
     */
    ListResult<CustomerBoughtGoodsResp> listBoughtGoods(Long businessId, Long customerId);

    /**
     * 客户浏览商品
     * @param businessId
     * @param customerId
     * @return
     */
    ListResult<CustomerBrowseGoodsResp> listBrowseGoods(Long businessId, Long customerId);

}
