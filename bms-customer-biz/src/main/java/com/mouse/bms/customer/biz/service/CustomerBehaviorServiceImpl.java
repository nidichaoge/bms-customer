package com.mouse.bms.customer.biz.service;


import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.mouse.bms.customer.api.response.CustomerBehaviorResp;
import com.mouse.bms.customer.api.response.CustomerBoughtGoodsResp;
import com.mouse.bms.customer.api.response.CustomerBrowseGoodsResp;
import com.mouse.bms.customer.api.response.CustomerDetailInfoResp;
import com.mouse.bms.customer.api.response.CustomerTagsResp;
import com.mouse.bms.customer.api.service.CustomerBehaviorService;
import com.mouse.bms.customer.biz.repository.CustomerRepository;
import com.mouse.bms.customer.biz.repository.TagRepository;
import com.mouse.bms.customer.common.enums.CommonResultEnum;
import com.mouse.bms.customer.common.response.ListResult;
import com.mouse.bms.customer.common.response.PlainResult;
import com.mouse.bms.customer.common.util.ListResults;
import com.mouse.bms.customer.common.util.PlainResults;
import com.mouse.bms.customer.dal.dao.Customer2TagDAO;
import com.mouse.bms.customer.dal.dao.RefundInfoDAO;
import com.mouse.bms.customer.dal.dao.TradeInfoDAO;
import com.mouse.bms.customer.dal.dataobject.Customer2TagDO;
import com.mouse.bms.customer.dal.dataobject.CustomerDO;
import com.mouse.bms.customer.dal.dataobject.CustomerInfoDO;
import com.mouse.bms.customer.dal.dataobject.RefundInfoDO;
import com.mouse.bms.customer.dal.dataobject.TagDO;
import com.mouse.bms.customer.dal.dataobject.TradeInfoDO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : CustomerBehaviorServiceImpl
 * @date : 2019/3/4 22:44
 * @description :
 */
@Service("customerBehaviorService")
@com.alibaba.dubbo.config.annotation.Service(interfaceClass = CustomerBehaviorService.class)
public class CustomerBehaviorServiceImpl implements CustomerBehaviorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerBehaviorServiceImpl.class);

    @Resource
    private TradeInfoDAO tradeInfoDAO;
    @Resource
    private RefundInfoDAO refundInfoDAO;
    @Resource
    private Customer2TagDAO customer2TagDAO;
    @Resource
    private TagRepository tagRepository;
    @Resource
    private CustomerRepository customerRepository;

    @Override
    public PlainResult<CustomerTagsResp> getCustomerTags(Long businessId, Long customerId) {
        try {
            Preconditions.checkArgument(0L < businessId && 0L < customerId);
            Set<Customer2TagDO> customer2TagDOS = customer2TagDAO.getCustomerTagIds(businessId, customerId);

            CustomerTagsResp customerTagsResp = new CustomerTagsResp()
                .setCustomerId(customerId).setTagNames(Lists.newArrayList());
            if (CollectionUtils.isEmpty(customer2TagDOS)) {
                return PlainResults.success(customerTagsResp);
            }
            Set<Long> tagIds = customer2TagDOS.stream().filter(Objects::nonNull).map(Customer2TagDO::getTagId)
                .filter(Objects::nonNull).collect(Collectors.toSet());

            Set<TagDO> tagDOS = tagRepository.getBatch(businessId, tagIds);

            customerTagsResp.setTagNames(tagDOS.stream().filter(Objects::nonNull).map(TagDO::getName)
                                             .filter(Objects::nonNull).collect(Collectors.toList()));
            return PlainResults.success(customerTagsResp);
        } catch (IllegalArgumentException e) {
            LOGGER.warn("CustomerBehaviorService | getCustomerTags, params is illegal,businessId:{}, customerId:{}",
                        businessId, customerId);
            return PlainResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (Exception e) {
            LOGGER.warn("CustomerBehaviorService | getCustomerTags, params is illegal,businessId:{}, customerId:{}",
                        businessId, customerId);
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    @Override
    public PlainResult<CustomerDetailInfoResp> getCustomerDetailInfo(Long businessId, Long customerId) {
        try {
            Preconditions.checkArgument(0L < businessId && 0L < customerId);
            CustomerDO customerDO = customerRepository.get(businessId, customerId);
            CustomerInfoDO customerInfoDO = customerRepository.getDetail(businessId, customerId);
            if (Objects.isNull(customerDO)) {
                return PlainResults
                    .error(CommonResultEnum.ERROR_DATA_NOT_EXISTS.code, CommonResultEnum.ERROR_DATA_NOT_EXISTS.message);
            }
            return PlainResults.success(buildCustomerDetailInfoResp(customerDO, customerInfoDO));
        } catch (IllegalArgumentException e) {
            LOGGER.warn("CustomerBehaviorService | getCustomerTags, params is illegal,businessId:{}, customerId:{}",
                        businessId, customerId);
            return PlainResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (Exception e) {
            LOGGER.warn("CustomerBehaviorService | getCustomerTags, params is illegal,businessId:{}, customerId:{}",
                        businessId, customerId);
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    @Override
    public PlainResult<CustomerBehaviorResp> getCustomerTradeInfo(Long businessId, Long customerId) {
        try {
            Preconditions.checkArgument(0L < businessId && 0L < customerId);

            TradeInfoDO tradeInfoDO = tradeInfoDAO.get(businessId, customerId);
            RefundInfoDO refundInfoDO = refundInfoDAO.get(businessId, customerId);
            return PlainResults.success(buildCustomerBehaviorResp(tradeInfoDO, refundInfoDO));
        } catch (IllegalArgumentException e) {
            LOGGER.warn("CustomerBehaviorService | getCustomerTags, params is illegal,businessId:{}, customerId:{}",
                        businessId, customerId);
            return PlainResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (Exception e) {
            LOGGER.warn("CustomerBehaviorService | getCustomerTags, params is illegal,businessId:{}, customerId:{}",
                        businessId, customerId);
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    @Override
    public ListResult<CustomerBoughtGoodsResp> listBoughtGoods(Long businessId, Long customerId) {
        try {
            Preconditions.checkArgument(0L < businessId && 0L < customerId);
            return ListResults.success(Lists.newArrayList());
        } catch (IllegalArgumentException e) {
            LOGGER.warn("CustomerBehaviorService | listBoughtGoods, params is illegal,businessId:{}, customerId:{}.",
                        businessId, customerId);
            return ListResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (Exception e) {
            LOGGER.error("CustomerBehaviorService | listBoughtGoods, exception, msg:{}.", e.getMessage());
            return ListResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    @Override
    public ListResult<CustomerBrowseGoodsResp> listBrowseGoods(Long businessId, Long customerId) {
        try {
            Preconditions.checkArgument(0L < businessId && 0L < customerId);
            return ListResults.success(Lists.newArrayList());
        } catch (IllegalArgumentException e) {
            LOGGER.warn("CustomerBehaviorService | listBrowseGoods, params is illegal,businessId:{}, customerId:{}",
                        businessId, customerId);
            return ListResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (Exception e) {
            LOGGER.error("CustomerBehaviorService | listBrowseGoods, exception, msg:{}.", e.getMessage());
            return ListResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    private CustomerDetailInfoResp buildCustomerDetailInfoResp(CustomerDO customerDO, CustomerInfoDO customerInfoDO) {
        CustomerDetailInfoResp customerDetailInfoResp = new CustomerDetailInfoResp()
            .setBusinessId(customerDO.getBusinessId())
            .setNickName(customerDO.getNickName())
            .setMobile(customerDO.getMobile())
            .setSex(customerDO.getGender())
            .setDescription(customerDO.getDescription())
            .setCreatedAt(customerDO.getCreatedAt());
        if (!Objects.isNull(customerInfoDO)) {
            customerDetailInfoResp.setBirthday(customerInfoDO.getBirthday())
                .setArea(customerInfoDO.getArea())
                .setAnniversary(customerInfoDO.getAnniversary())
                .setWeChat(customerInfoDO.getWeChat());
        }
        return customerDetailInfoResp;
    }

    private CustomerBehaviorResp buildCustomerBehaviorResp(TradeInfoDO tradeInfoDO, RefundInfoDO refundInfoDO) {
        CustomerBehaviorResp customerBehaviorResp = new CustomerBehaviorResp().setTradeCount(0)
            .setTradeAmount((double) 0)
            .setRefundCount(0)
            .setRefundAmount((double) 0)
            .setLastTradeTime(null)
            .setRealPrice((double) 0);
        if (Objects.isNull(tradeInfoDO)) {
            return customerBehaviorResp;
        }
        customerBehaviorResp.setTradeCount(tradeInfoDO.getTradeCount())
            .setTradeAmount(tradeInfoDO.getTotalAmount())
            .setRealPrice(tradeInfoDO.getTotalAmount())
            .setLastTradeTime(tradeInfoDO.getLastTradeAt());
        if (Objects.isNull(refundInfoDO)) {
            return customerBehaviorResp;
        }
        return customerBehaviorResp
            .setRefundCount(refundInfoDO.getRefundCount())
            .setRefundAmount(refundInfoDO.getTotalRefundAmount())
            .setRealPrice(tradeInfoDO.getTotalAmount() - refundInfoDO.getTotalRefundAmount());
    }

}
