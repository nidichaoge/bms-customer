package com.mouse.bms.customer.api.service;

import com.mouse.bms.customer.api.request.TagsToCustomerAddBatchRequest;
import com.mouse.bms.customer.api.request.TagsToCustomerAddRequest;
import com.mouse.bms.customer.common.response.PlainResult;

import java.util.List;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : TagCustomerService
 * @date : 2019/3/4 20:07
 * @description : 标签和客户关系服务
 */
public interface TagCustomerService {

    /**
     * 给指定客户添加标签
     * @param tagsToCustomerAddRequest
     * @return
     */
    PlainResult<Boolean> addTagsToCustomer(TagsToCustomerAddRequest tagsToCustomerAddRequest);

    /**
     * 移除指定客户标签
     * @param businessId
     * @param customerId
     * @param tagIds
     * @return
     */
    PlainResult<Boolean> removeTagsToCustomer(Long businessId, Long customerId, List<Long> tagIds);

    /**
     * 给客户批量打标
     * @param tagsToCustomerAddBatchRequest
     * @return
     */
    PlainResult<Boolean> addTagBatch(TagsToCustomerAddBatchRequest tagsToCustomerAddBatchRequest);

    /**
     * 批量移除客户标签
     * @param businessId
     * @param customerIds
     * @param tagId
     * @return
     */
    PlainResult<Boolean> removeTagBatch(Long businessId, List<Long> customerIds, Long tagId);

}
