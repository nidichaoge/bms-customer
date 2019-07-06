package com.mouse.bms.customer.web.controller;

import com.alibaba.fastjson.JSON;
import com.mouse.bms.customer.api.request.CustomerAddRequest;
import com.mouse.bms.customer.api.request.CustomerEditRequest;
import com.mouse.bms.customer.api.request.CustomerQueryRequest;
import com.mouse.bms.customer.api.response.CustomerQueryResp;
import com.mouse.bms.customer.api.service.CustomerService;
import com.mouse.bms.customer.common.response.ListResult;
import com.mouse.bms.customer.common.response.PlainResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import io.swagger.annotations.ApiOperation;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : CustomerController
 * @date : 2019/3/4 23:15
 * @description :
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @Resource
    private CustomerService customerService;

    @ApiOperation(value = "添加客户")
    @PostMapping("/add")
    public PlainResult<Long> addCustomer(@RequestBody CustomerAddRequest customerAddRequest) {
        LOGGER.info("CustomerController | addCustomer, customerAddRequest:{}.", customerAddRequest);
        return customerService.addCustomer(customerAddRequest);
    }

    @ApiOperation(value = "编辑客户")
    @PutMapping("/edit")
    public PlainResult<Boolean> editCustomer(@RequestBody CustomerEditRequest customerEditRequest) {
        LOGGER.info("CustomerController | editCustomer, customerEditRequest:{}.", customerEditRequest);
        return customerService.editCustomer(customerEditRequest);
    }

    @ApiOperation(value = "根据手机号查找客户")
    @GetMapping("/search")
    public PlainResult<CustomerQueryResp> searchCustomer(@RequestParam Long businessId, @RequestParam String mobile) {
        LOGGER.info("CustomerController | searchCustomer, businessId:{}, mobile:{}.", businessId, mobile);
        return customerService.searchCustomer(businessId, mobile);
    }

    @ApiOperation(value = "查询客户信息")
    @GetMapping("/list")
    public ListResult<CustomerQueryResp> listCustomers(@RequestParam String query) {
        CustomerQueryRequest customerQueryRequest = JSON.parseObject(query, CustomerQueryRequest.class);
        LOGGER.info("CustomerController | listCustomers, customerAddRequest:{}.", customerQueryRequest);
        return customerService.listCustomers(customerQueryRequest);
    }

    @ApiOperation(value = "查询客户数量")
    @GetMapping("/count")
    public PlainResult<Long> countCustomer(@RequestParam Long businessId) {
        LOGGER.info("CustomerController | countCustomer, businessId:{}.", businessId);
        return customerService.countCustomer(businessId);
    }

}
