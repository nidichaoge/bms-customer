package com.mouse.bms.customer.api.service;

import com.mouse.bms.customer.api.request.CustomerAddRequest;
import com.mouse.bms.customer.api.request.CustomerEditRequest;
import com.mouse.bms.customer.api.request.CustomerQueryRequest;
import com.mouse.bms.customer.api.response.CustomerQueryResp;
import com.mouse.bms.customer.common.response.ListResult;
import com.mouse.bms.customer.common.response.PlainResult;
/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : CustomerManagerSerivce
 * @date : 2019/3/2 14:13
 * @description : 客户服务
 */
public interface CustomerService {

    /**
     * 添加客户
     * @param customerAddRequest
     * @return
     */
    PlainResult<Long> addCustomer(CustomerAddRequest customerAddRequest);

    /**
     * 编辑客户
     * @param customerEditRequest
     * @return
     */
    PlainResult<Boolean> editCustomer(CustomerEditRequest customerEditRequest);

    /**
     * 根据手机号查找客户
     * @param businessId
     * @param mobile
     * @return
     */
    PlainResult<CustomerQueryResp> searchCustomer(Long businessId, String mobile);

    /**
     * 查询客户
     * @param customerQueryRequest
     * @return
     */
    ListResult<CustomerQueryResp> listCustomers(CustomerQueryRequest customerQueryRequest);

    /**
     * 客户数量
     * @param businessId
     * @return
     */
    PlainResult<Long> countCustomer(Long businessId);

}
