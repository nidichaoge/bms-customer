package com.mouse.bms.customer.biz.service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mouse.bms.customer.api.request.TagsToCustomerAddBatchRequest;
import com.mouse.bms.customer.api.request.TagsToCustomerAddRequest;
import com.mouse.bms.customer.api.service.TagCustomerService;
import com.mouse.bms.customer.biz.exception.TagCustomerException;
import com.mouse.bms.customer.biz.exception.TagException;
import com.mouse.bms.customer.biz.repository.CustomerRepository;
import com.mouse.bms.customer.biz.repository.TagRepository;
import com.mouse.bms.customer.common.enums.CommonResultEnum;
import com.mouse.bms.customer.common.response.PlainResult;
import com.mouse.bms.customer.common.util.PlainResults;
import com.mouse.bms.customer.dal.dao.Customer2TagDAO;
import com.mouse.bms.customer.dal.dao.Tag2CustomerDAO;
import com.mouse.bms.customer.dal.dataobject.Customer2TagDO;
import com.mouse.bms.customer.dal.dataobject.CustomerDO;
import com.mouse.bms.customer.dal.dataobject.Tag2CustomerDO;
import com.mouse.bms.customer.dal.dataobject.TagDO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
 * @fileName : TagCustomerService
 * @date : 2019/3/4 22:44
 * @description :
 */
@Service("tagCustomerService")
@com.alibaba.dubbo.config.annotation.Service(interfaceClass = TagCustomerService.class)
public class TagCustomerServiceImpl implements TagCustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TagCustomerServiceImpl.class);

    @Resource
    private TagRepository tagRepository;
    @Resource
    private CustomerRepository customerRepository;
    @Resource
    private Tag2CustomerDAO tag2CustomerDAO;
    @Resource
    private Customer2TagDAO customer2TagDAO;

    //todo 不存在的不会自动创建
    @Override
    public PlainResult<Boolean> addTagsToCustomer(TagsToCustomerAddRequest tagsToCustomerAddRequest) {
        try {
            Preconditions.checkArgument(!Objects.isNull(tagsToCustomerAddRequest));
            Long businessId = tagsToCustomerAddRequest.getBusinessId();
            Long customerId = tagsToCustomerAddRequest.getCustomerId();
            List<Long> tagIds = tagsToCustomerAddRequest.getTagIds();
            Preconditions.checkArgument(0L < businessId && 0L < customerId && CollectionUtils.isNotEmpty(tagIds));
            CustomerDO customerDO = customerRepository.get(businessId, customerId);
            Set<TagDO> tagDOS = tagRepository.getBatch(businessId, Sets.newHashSet(tagIds));
            if (CollectionUtils.isEmpty(tagDOS) || Objects.isNull(customerDO)) {
                throw new TagCustomerException("customer or tags not exists");
            }
            List<Long> collect = tagDOS.stream().filter(Objects::nonNull).map(TagDO::getId)
                .collect(Collectors.toList());
            for (Long tagId : collect) {
                long
                    insert =
                    tag2CustomerDAO.insert(
                        buildTag2CustomerDO(businessId, customerId, tagId, tagsToCustomerAddRequest.getTagSource()));
                if (0 < insert) {
                    tagRepository.plusTagCustomerNum(businessId, tagId, 1L);
                }
                customer2TagDAO.insert(
                    buildCustomer2TagDO(businessId, customerId, tagId, tagsToCustomerAddRequest.getTagSource()));
            }
            return PlainResults.success(Boolean.TRUE);
        } catch (IllegalArgumentException e) {
            LOGGER.warn("TagCustomerService | addTagsToCustomer, params is illegal, tagsToCustomerAddRequest:{}.",
                        tagsToCustomerAddRequest);
            return PlainResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (TagException | TagCustomerException e) {
            LOGGER.warn("TagCustomerService | addTagsToCustomer, customer or tags not exists.");
            return PlainResults
                .error(CommonResultEnum.ERROR_DATA_NOT_EXISTS.code, CommonResultEnum.ERROR_DATA_NOT_EXISTS.message);
        } catch (Exception e) {
            LOGGER.error("TagCustomerService | addTagsToCustomer, exception, msg:{}.", e.getMessage());
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    @Override
    public PlainResult<Boolean> removeTagsToCustomer(Long businessId, Long customerId, List<Long> tagIds) {
        try {
            Preconditions.checkArgument(0L < businessId && 0L < customerId && CollectionUtils.isNotEmpty(tagIds));
            CustomerDO customerDO = customerRepository.get(businessId, customerId);
            Set<TagDO> tagDOS = tagRepository.getBatch(businessId, Sets.newHashSet(tagIds));
            if (CollectionUtils.isEmpty(tagDOS) || Objects.isNull(customerDO)) {
                throw new TagCustomerException("customer or tags not exists");
            }
            List<Long> collect = tagDOS.stream().filter(Objects::nonNull).map(TagDO::getId)
                .collect(Collectors.toList());
            for (Long tagId : collect) {
                boolean remove = tag2CustomerDAO.remove(businessId, tagId, customerId);
                if (remove) {
                    tagRepository.subtractTagCustomerNum(businessId, tagId, 1L);
                }
            }
            customer2TagDAO.removeTagBatch(businessId, customerId, Sets.newHashSet(collect));
            return PlainResults.success(Boolean.TRUE);
        } catch (IllegalArgumentException e) {
            LOGGER.warn(
                "TagCustomerService | removeTagsToCustomer, params is illegal, businessId:{}, customerId:{}, tagIds:{}.",
                businessId, customerId, tagIds);
            return PlainResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (TagException | TagCustomerException e) {
            LOGGER.warn("TagCustomerService | removeTagsToCustomer, customer or tags not exists.");
            return PlainResults
                .error(CommonResultEnum.ERROR_DATA_NOT_EXISTS.code, CommonResultEnum.ERROR_DATA_NOT_EXISTS.message);
        } catch (Exception e) {
            LOGGER.error("TagCustomerService | removeTagsToCustomer, exception, msg:{}.", e.getMessage());
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    @Override
    public PlainResult<Boolean> addTagBatch(TagsToCustomerAddBatchRequest tagsToCustomerAddBatchRequest) {
        try {
            Preconditions.checkArgument(!Objects.isNull(tagsToCustomerAddBatchRequest));
            Long businessId = tagsToCustomerAddBatchRequest.getBusinessId();
            List<Long> customerIds = tagsToCustomerAddBatchRequest.getCustomerIds();
            Long tagId = tagsToCustomerAddBatchRequest.getTagId();
            Preconditions.checkArgument(0L < businessId && 0L < tagId && CollectionUtils.isNotEmpty(customerIds));
            Set<CustomerDO> customerDOS = customerRepository.getBatch(businessId, Sets.newHashSet(customerIds));
            TagDO tagDO = tagRepository.get(businessId, tagId);
            if (CollectionUtils.isEmpty(customerDOS) || Objects.isNull(tagDO)) {
                throw new TagCustomerException("customer or tags not exists");
            }
            List<Long> collect = customerDOS.stream().filter(Objects::nonNull).map(CustomerDO::getId)
                .collect(Collectors.toList());
            for (Long customerId : collect) {
                long
                    insert =
                    tag2CustomerDAO.insert(buildTag2CustomerDO(businessId, customerId, tagId,
                                                               tagsToCustomerAddBatchRequest.getTagSource()));
                if (0 < insert) {
                    tagRepository.plusTagCustomerNum(businessId, tagId, 1L);
                }
                customer2TagDAO.insert(
                    buildCustomer2TagDO(businessId, customerId, tagId, tagsToCustomerAddBatchRequest.getTagSource()));
            }
            return PlainResults.success(Boolean.TRUE);
        } catch (IllegalArgumentException e) {
            LOGGER.warn(
                "TagCustomerService | addTagBatch, params is illegal, tagsToCustomerAddBatchRequest:{}.",
                tagsToCustomerAddBatchRequest);
            return PlainResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (TagException | TagCustomerException e) {
            LOGGER.warn("TagCustomerService | addTagBatch, customer or tags not exists.");
            return PlainResults
                .error(CommonResultEnum.ERROR_DATA_NOT_EXISTS.code, CommonResultEnum.ERROR_DATA_NOT_EXISTS.message);
        } catch (Exception e) {
            LOGGER.error("TagCustomerService | addTagBatch, exception, msg:{}.", e.getMessage());
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    @Override
    public PlainResult<Boolean> removeTagBatch(Long businessId, List<Long> customerIds, Long tagId) {
        try {
            Preconditions.checkArgument(0L < businessId && 0L < tagId && CollectionUtils.isNotEmpty(customerIds));
            customerIds.forEach(customerId -> removeTagsToCustomer(businessId, customerId, Lists.newArrayList(tagId)));
            return PlainResults.success(Boolean.TRUE);
        } catch (IllegalArgumentException e) {
            LOGGER.warn(
                "TagCustomerService | removeTagBatch, params is illegal, businessId:{}, customerIds:{}, tagId:{}.",
                businessId, customerIds, tagId);
            return PlainResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (Exception e) {
            LOGGER.error("TagCustomerService | removeTagBatch, exception, msg:{}.", e.getMessage());
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }

    }

    private Customer2TagDO buildCustomer2TagDO(Long businessId, Long customerId, Long tagId, Short tagSource) {
        return new Customer2TagDO()
            .setBusinessId(businessId)
            .setCustomerId(customerId)
            .setTagId(tagId)
            .setAutoTagged(1 == tagSource)
            .setTagSource(tagSource)
            .setCreatedAt(LocalDateTime.now());
    }

    private Tag2CustomerDO buildTag2CustomerDO(Long businessId, Long customerId, Long tagId, Short tagSource) {
        return new Tag2CustomerDO()
            .setBusinessId(businessId)
            .setTagId(tagId)
            .setCustomerId(customerId)
            .setAutoTagged(1 == tagSource)
            .setTagSource(tagSource)
            .setCreatedAt(LocalDateTime.now());
    }

}
