package com.mouse.bms.customer.biz.service;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.google.common.base.Preconditions;
import com.mouse.bms.customer.api.request.CustomerAddRequest;
import com.mouse.bms.customer.api.request.CustomerEditRequest;
import com.mouse.bms.customer.api.request.CustomerQueryRequest;
import com.mouse.bms.customer.api.response.CustomerQueryResp;
import com.mouse.bms.customer.api.service.CustomerService;
import com.mouse.bms.customer.biz.exception.CustomerException;
import com.mouse.bms.customer.biz.repository.CustomerRepository;
import com.mouse.bms.customer.common.enums.CommonResultEnum;
import com.mouse.bms.customer.common.response.ListResult;
import com.mouse.bms.customer.common.response.PlainResult;
import com.mouse.bms.customer.common.util.ListResults;
import com.mouse.bms.customer.common.util.PlainResults;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : CustomerService
 * @date : 2019/3/4 22:44
 * @description :
 */
@Service("customerService")
@com.alibaba.dubbo.config.annotation.Service(interfaceClass = CustomerService.class)
public class CustomerServiceImpl implements CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Resource
    private CustomerRepository customerRepository;

    @Override
    public PlainResult<Long> addCustomer(CustomerAddRequest customerAddRequest) {
        try {
            Preconditions.checkArgument(!Objects.isNull(customerAddRequest));
            Long businessId = customerAddRequest.getBusinessId();
            String nickName = customerAddRequest.getNickName();
            String mobile = customerAddRequest.getMobile();
            Preconditions
                .checkArgument(0L < businessId && StringUtils.isNotEmpty(nickName) && StringUtils.isNotEmpty(mobile));
            Long customerId = customerRepository.addCustomer(customerAddRequest);
            return PlainResults.success(customerId);
        } catch (IllegalArgumentException e) {
            LOGGER.warn("CustomerService | addCustomer, params is illegal, customerAddRequest:{}.", customerAddRequest);
            return PlainResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (CustomerException e) {
            LOGGER.warn("CustomerService | addCustomer, customer already exists.");
            return PlainResults
                .error(CommonResultEnum.ERROR_DATA_EXISTS.code, CommonResultEnum.ERROR_DATA_EXISTS.message);
        } catch (Exception e) {
            LOGGER.error("CustomerService | addCustomer, exception, msg:{}.", e.getMessage());
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    @Override
    public PlainResult<Boolean> editCustomer(CustomerEditRequest customerEditRequest) {
        try {
            Preconditions.checkArgument(!Objects.isNull(customerEditRequest));
            Long businessId = customerEditRequest.getBusinessId();
            Long customerId = customerEditRequest.getCustomerId();
            String nickName = customerEditRequest.getNickName();
            String mobile = customerEditRequest.getMobile();
            Preconditions.checkArgument(0L < businessId && 0L < customerId
                                        && StringUtils.isNotEmpty(nickName) && StringUtils.isNotEmpty(mobile));
            Boolean editCustomer = customerRepository.editCustomer(customerEditRequest);
            return PlainResults.success(editCustomer);
        } catch (IllegalArgumentException e) {
            LOGGER
                .warn("CustomerService | editCustomer, params is illegal, customerEditRequest:{}.",
                      customerEditRequest);
            return PlainResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (CustomerException e) {
            LOGGER.warn("CustomerService | editCustomer, customer does not exists.");
            return PlainResults
                .error(CommonResultEnum.ERROR_DATA_NOT_EXISTS.code, CommonResultEnum.ERROR_DATA_NOT_EXISTS.message);
        } catch (Exception e) {
            LOGGER.error("CustomerService | editCustomer, exception, msg:{}.", e.getMessage());
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    @Override
    public PlainResult<CustomerQueryResp> searchCustomer(Long businessId, String mobile) {
        try {
            Preconditions.checkArgument(0L < businessId && StringUtils.isNotEmpty(mobile));
            CustomerQueryResp customerQueryResp = customerRepository.getByMobile(businessId, mobile);
            return PlainResults.success(customerQueryResp);
        } catch (IllegalArgumentException e) {
            LOGGER.warn("CustomerService | listCustomers, params is illegal, businessId:{}, mobile:{}.",
                        businessId, mobile);
            return PlainResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (Exception e) {
            LOGGER.error("CustomerService | listCustomers, exception, msg:{}.", e.getMessage());
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    @Override
    public ListResult<CustomerQueryResp> listCustomers(CustomerQueryRequest customerQueryRequest) {
        try {
            Preconditions.checkArgument(!Objects.isNull(customerQueryRequest));
            Long businessId = customerQueryRequest.getBusinessId();
            Integer page = customerQueryRequest.getPage();
            Integer pageSize = customerQueryRequest.getPageSize();
            Preconditions.checkArgument(0L < businessId && 0 <= page && 0 < pageSize);

            List<CustomerQueryResp> customerQueryResps = customerRepository.listCustomers(customerQueryRequest);
            return ListResults.success(customerQueryResps);
        } catch (IllegalArgumentException e) {
            LOGGER.warn("CustomerService | listCustomers, params is illegal, customerAddRequest:{}.",
                        customerQueryRequest);
            return ListResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (Exception e) {
            LOGGER.error("CustomerService | listCustomers, exception, msg:{}.", e.getMessage());
            return ListResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    @Override
    public PlainResult<Long> countCustomer(Long businessId) {
        try {
            Preconditions.checkArgument(0L < businessId);

            Long countCustomer = customerRepository.countCustomer(businessId);
            return PlainResults.success(countCustomer);
        } catch (IllegalArgumentException e) {
            LOGGER.warn("CustomerService | listCustomers, params is illegal, businessId:{}.", businessId);
            return PlainResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (Exception e) {
            LOGGER.error("CustomerService | listCustomers, exception, msg:{}.", e.getMessage());
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

}
