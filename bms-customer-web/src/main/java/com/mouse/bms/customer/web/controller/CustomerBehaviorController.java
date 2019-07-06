package com.mouse.bms.customer.web.controller;


import com.mouse.bms.customer.api.response.CustomerBehaviorResp;
import com.mouse.bms.customer.api.response.CustomerBoughtGoodsResp;
import com.mouse.bms.customer.api.response.CustomerBrowseGoodsResp;
import com.mouse.bms.customer.api.response.CustomerDetailInfoResp;
import com.mouse.bms.customer.api.response.CustomerTagsResp;
import com.mouse.bms.customer.api.service.CustomerBehaviorService;
import com.mouse.bms.customer.common.response.ListResult;
import com.mouse.bms.customer.common.response.PlainResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import io.swagger.annotations.ApiOperation;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : CustomerBehaviorController
 * @date : 2019/3/4 23:16
 * @description :
 */
@RestController
@RequestMapping("/behavior")
public class CustomerBehaviorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerBehaviorController.class);

    @Resource
    private CustomerBehaviorService customerBehaviorService;

    @ApiOperation(value = "查询客户详细信息")
    @GetMapping("/info")
    public PlainResult<CustomerDetailInfoResp> getCustomerDetailInfo(@RequestParam Long businessId,
                                                                     @RequestParam Long customerId) {
        LOGGER.info("CustomerBehaviorController | getCustomerDetailInfo, businessId:{}, customerId:{}.", businessId,
                    customerId);
        return customerBehaviorService.getCustomerDetailInfo(businessId, customerId);
    }

    @ApiOperation(value = "查询客户标签信息")
    @GetMapping("/tag")
    public PlainResult<CustomerTagsResp> getCustomerTags(@RequestParam Long businessId, @RequestParam Long customerId) {
        LOGGER.info("CustomerBehaviorController | getCustomerTags, businessId:{}, customerId:{}.", businessId,
                    customerId);
        return customerBehaviorService.getCustomerTags(businessId, customerId);
    }

    @ApiOperation(value = "查询客户交易信息")
    @GetMapping("/trade")
    public PlainResult<CustomerBehaviorResp> getCustomerTradeInfo(@RequestParam Long businessId,
                                                                  @RequestParam Long customerId) {
        LOGGER.info("CustomerBehaviorController | getCustomerTradeInfo, businessId:{}, customerId:{}.", businessId,
                    customerId);
        return customerBehaviorService.getCustomerTradeInfo(businessId, customerId);
    }

    @ApiOperation(value = "查询客户购买信息")
    @GetMapping("/bought")
    public ListResult<CustomerBoughtGoodsResp> listBoughtGoods(@RequestParam Long businessId,
                                                               @RequestParam Long customerId) {
        LOGGER.info("CustomerBehaviorController | listBoughtGoods, businessId:{}, customerId:{}.", businessId,
                    customerId);
        return customerBehaviorService.listBoughtGoods(businessId, customerId);
    }

    @ApiOperation(value = "查询客户浏览信息")
    @GetMapping("/browse")
    public ListResult<CustomerBrowseGoodsResp> listBrowseGoods(@RequestParam Long businessId,
                                                               @RequestParam Long customerId) {
        LOGGER.info("CustomerBehaviorController | listBrowseGoods, businessId:{}, customerId:{}.", businessId,
                    customerId);
        return customerBehaviorService.listBrowseGoods(businessId, customerId);
    }

}
