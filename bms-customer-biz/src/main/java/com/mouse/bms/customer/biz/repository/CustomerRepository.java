package com.mouse.bms.customer.biz.repository;


import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.mouse.bms.customer.api.request.CustomerAddRequest;
import com.mouse.bms.customer.api.request.CustomerDetailInfo;
import com.mouse.bms.customer.api.request.CustomerEditRequest;
import com.mouse.bms.customer.api.request.CustomerQueryRequest;
import com.mouse.bms.customer.api.response.CustomerQueryResp;
import com.mouse.bms.customer.biz.exception.CustomerException;
import com.mouse.bms.customer.dal.dao.CustomerDAO;
import com.mouse.bms.customer.dal.dao.CustomerInfoDAO;
import com.mouse.bms.customer.dal.dao.TradeInfoDAO;
import com.mouse.bms.customer.dal.dataobject.CustomerDO;
import com.mouse.bms.customer.dal.dataobject.CustomerInfoDO;
import com.mouse.bms.customer.dal.dataobject.TradeInfoDO;

import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : CustomerRepository
 * @date : 2019/3/18 18:27
 * @description :
 */
@Component("customerRepository")
public class CustomerRepository {

    private static final String ORDER = "desc";
    private static final String ORDER_BY = "created_at";

    @Resource
    private CustomerDAO customerDAO;
    @Resource
    private CustomerInfoDAO customerInfoDAO;
    @Resource
    private TradeInfoDAO tradeInfoDAO;

    public Long addCustomer(CustomerAddRequest customerAddRequest) throws Exception {
        Preconditions.checkArgument(!Objects.isNull(customerAddRequest));
        Long businessId = customerAddRequest.getBusinessId();
        String nickName = customerAddRequest.getNickName();
        String mobile = customerAddRequest.getMobile();
        CustomerDetailInfo customerDetailInfo = customerAddRequest.getCustomerDetailInfo();
        Preconditions
            .checkArgument(0L < businessId && StringUtils.isNotEmpty(nickName) && StringUtils.isNotEmpty(mobile));

        CustomerDO exist = customerDAO.getByMobile(businessId, mobile);
        if (!Objects.isNull(exist)) {
            throw new CustomerException("customer already exists");
        }
        CustomerDO customerDO = buildCustomerDO(businessId, nickName, mobile, customerAddRequest.getGender(), customerAddRequest.getDescription());
        customerDAO.insert(customerDO);
        if (!Objects.isNull(customerDetailInfo)) {
            customerInfoDAO.insert(buildCustomerInfoDO(businessId, customerDO.getId(), customerDetailInfo.getWeChat(),
                                                       customerDetailInfo.getArea(), customerDetailInfo.getBirthday(),
                                                       customerDetailInfo.getAnniversary()));
        }
        return customerDO.getId();
    }

    public Boolean editCustomer(CustomerEditRequest customerEditRequest) throws Exception {
        Preconditions.checkArgument(!Objects.isNull(customerEditRequest));
        Long businessId = customerEditRequest.getBusinessId();
        Long customerId = customerEditRequest.getCustomerId();
        String nickName = customerEditRequest.getNickName();
        String mobile = customerEditRequest.getMobile();
        CustomerDetailInfo customerDetailInfo = customerEditRequest.getCustomerDetailInfo();
        Preconditions.checkArgument(0L < businessId && 0L < customerId
                                    && StringUtils.isNotEmpty(nickName) && StringUtils.isNotEmpty(mobile));

        CustomerDO exist = customerDAO.get(businessId, customerId);
        if (Objects.isNull(exist)) {
            throw new CustomerException("customer does not exists");
        }
        CustomerInfoDO existInfo = customerInfoDAO.get(businessId, customerId);
        boolean update = customerDAO.update(businessId, customerId, nickName, mobile,
                                            customerEditRequest.getGender(), customerEditRequest.getDescription());
        if (Objects.isNull(customerDetailInfo)) {
            if (!Objects.isNull(existInfo)) {
                customerInfoDAO.delete(businessId, customerId);
            }
        } else {
            if (Objects.isNull(existInfo)) {
                customerInfoDAO.insert(buildCustomerInfoDO(businessId, customerId, customerDetailInfo.getWeChat(),
                                                           customerDetailInfo.getArea(),
                                                           customerDetailInfo.getBirthday(),
                                                           customerDetailInfo.getAnniversary()));
            } else {
                customerInfoDAO
                    .update(businessId, customerId, customerDetailInfo.getArea(), customerDetailInfo.getWeChat(),
                            customerDetailInfo.getBirthday(), customerDetailInfo.getAnniversary());
            }
        }
        return update;
    }

    public List<CustomerQueryResp> listCustomers(CustomerQueryRequest customerQueryRequest) throws Exception {
        Preconditions.checkArgument(!Objects.isNull(customerQueryRequest));
        Long businessId = customerQueryRequest.getBusinessId();
        Integer page = customerQueryRequest.getPage();
        Integer pageSize = customerQueryRequest.getPageSize();
        Preconditions.checkArgument(0L < businessId && 0 <= page && 0 < pageSize);

        setParam(customerQueryRequest);

        List<CustomerDO> customerDOS = customerDAO.list(businessId, customerQueryRequest.getKeyword(), page*pageSize,
                                                        pageSize, customerQueryRequest.getOrder(),
                                                        customerQueryRequest.getOrderBy());
        if (CollectionUtils.isEmpty(customerDOS)) {
            return Lists.newArrayList();
        }
        return customerDOS.stream().filter(Objects::nonNull).map(this::buildCustomerQueryResp)
            .collect(Collectors.toList());
    }

    public Set<CustomerDO> getBatch(Long businessId, Set<Long> customerIds) throws Exception {
        Preconditions.checkArgument(0L < businessId && CollectionUtils.isNotEmpty(customerIds));
        return customerDAO.getBatch(businessId, customerIds);

    }

    public Long countCustomer(Long businessId) throws Exception {
        Preconditions.checkArgument(0L < businessId);
        return customerDAO.getCount(businessId, (short) 0);
    }

    public CustomerDO get(Long businessId, Long customerId) throws Exception {
        Preconditions.checkArgument(0L < businessId && 0L < customerId);
        return customerDAO.get(businessId, customerId);
    }

    public CustomerInfoDO getDetail(Long businessId, Long customerId) throws Exception {
        Preconditions.checkArgument(0L < businessId && 0L < customerId);
        return customerInfoDAO.get(businessId, customerId);
    }

    public CustomerQueryResp getByMobile(Long businessId, String mobile) throws Exception {
        Preconditions.checkArgument(0L < businessId && StringUtils.isNotEmpty(mobile));
        CustomerDO customerDO = customerDAO.getByMobile(businessId, mobile);
        if (Objects.isNull(customerDO)) {
            return null;
        }
        return buildCustomerQueryResp(customerDO);
    }

    private CustomerInfoDO buildCustomerInfoDO(Long businessId, Long customerId, String weChat, String area,
                                               LocalDateTime birthday, LocalDateTime anniversary) {
        return new CustomerInfoDO()
            .setBusinessId(businessId)
            .setCustomerId(customerId)
            .setWeChat(weChat)
            .setArea(area)
            .setBirthday(birthday)
            .setAnniversary(anniversary)
            .setCreatedAt(LocalDateTime.now());
    }

    private CustomerDO buildCustomerDO(Long businessId, String nickName, String mobile, Short gender,
                                       String description) {
        return new CustomerDO()
            .setBusinessId(businessId)
            .setNickName(nickName)
            .setMobile(mobile)
            .setGender(gender)
            .setDescription(description)
            .setCreatedAt(LocalDateTime.now());
    }

    private void setParam(CustomerQueryRequest customerQueryRequest) {
        customerQueryRequest
            .setKeyword("".equals(customerQueryRequest.getKeyword()) ? null : customerQueryRequest.getKeyword());
        customerQueryRequest
            .setOrder(StringUtils.isEmpty(customerQueryRequest.getOrder()) ? ORDER : customerQueryRequest.getOrder());
        customerQueryRequest
            .setOrderBy(
                StringUtils.isEmpty(customerQueryRequest.getOrderBy()) ? ORDER_BY : customerQueryRequest.getOrderBy());
    }

    //TODO 积分
    private CustomerQueryResp buildCustomerQueryResp(CustomerDO customerDO) {
        TradeInfoDO tradeInfoDO = tradeInfoDAO.get(customerDO.getBusinessId(), customerDO.getId());
        return new CustomerQueryResp()
            .setCustomerId(customerDO.getId())
            .setNickName(customerDO.getNickName())
            .setMobile(customerDO.getMobile())
            .setCreatedAt(customerDO.getCreatedAt())
            .setDescription(customerDO.getDescription())
            .setTradeCount(Objects.isNull(tradeInfoDO) ? 0 : tradeInfoDO.getTradeCount())
            .setLastTradeTime(Objects.isNull(tradeInfoDO) ? null : tradeInfoDO.getLastTradeAt())
            .setCredit(0L);
    }

}
