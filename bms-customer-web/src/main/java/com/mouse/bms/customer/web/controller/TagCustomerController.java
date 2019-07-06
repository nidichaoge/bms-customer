package com.mouse.bms.customer.web.controller;

import com.mouse.bms.customer.api.request.TagsToCustomerAddBatchRequest;
import com.mouse.bms.customer.api.request.TagsToCustomerAddRequest;
import com.mouse.bms.customer.api.service.TagCustomerService;
import com.mouse.bms.customer.common.response.PlainResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.annotation.Resource;

import io.swagger.annotations.ApiOperation;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : TagCustomerController
 * @date : 2019/3/4 23:16
 * @description :
 */
@RestController
@RequestMapping("/tagCustomer")
public class TagCustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TagCustomerController.class);

    @Resource
    private TagCustomerService tagCustomerService;

    @ApiOperation(value = "给客户打多个标签")
    @PostMapping("/add")
    public PlainResult<Boolean> addTagsToCustomer(@RequestBody TagsToCustomerAddRequest tagsToCustomerAddRequest) {
        LOGGER
            .info("TagCustomerController | addTagsToCustomer, tagsToCustomerAddRequest:{}.", tagsToCustomerAddRequest);
        return tagCustomerService.addTagsToCustomer(tagsToCustomerAddRequest);
    }

    @ApiOperation(value = "删除客户下多个标签")
    @DeleteMapping("/remove")
    public PlainResult<Boolean> removeTagsToCustomer(@RequestParam Long businessId, @RequestParam Long customerId,
                                                     @RequestParam List<Long> tagIds) {
        LOGGER.info("TagCustomerController | removeTagsToCustomer, businessId:{}, customerId:{}, tagIds:{}.",
                    businessId, customerId, tagIds);
        return tagCustomerService.removeTagsToCustomer(businessId, customerId, tagIds);
    }

    @ApiOperation(value = "给多个客户打标签")
    @PostMapping("/addBatch")
    public PlainResult<Boolean> addTagBatch(@RequestBody TagsToCustomerAddBatchRequest tagsToCustomerAddBatchRequest) {
        LOGGER.info("TagCustomerController | addTagBatch, tagsToCustomerAddBatchRequest:{}.",
                    tagsToCustomerAddBatchRequest);
        return tagCustomerService.addTagBatch(tagsToCustomerAddBatchRequest);
    }

    @ApiOperation(value = "删除多个客户下的标签")
    @DeleteMapping("/removeBatch")
    public PlainResult<Boolean> removeTagBatch(@RequestParam Long businessId, @RequestParam List<Long> customerIds,
                                               @RequestParam Long tagId) {
        LOGGER.info("TagCustomerController | removeTagBatch, businessId:{}, customerIds:{}, tagId:{}.", businessId,
                    customerIds, tagId);
        return tagCustomerService.removeTagBatch(businessId, customerIds, tagId);
    }

}
