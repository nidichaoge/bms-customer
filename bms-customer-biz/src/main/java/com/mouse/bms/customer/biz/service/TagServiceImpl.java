package com.mouse.bms.customer.biz.service;


import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.google.common.base.Preconditions;
import com.mouse.bms.customer.api.request.TagCreateRequest;
import com.mouse.bms.customer.api.request.TagEditRequest;
import com.mouse.bms.customer.api.request.TagQueryRequest;
import com.mouse.bms.customer.api.response.TagQueryResp;
import com.mouse.bms.customer.api.service.TagService;
import com.mouse.bms.customer.biz.exception.TagException;
import com.mouse.bms.customer.biz.repository.TagRepository;
import com.mouse.bms.customer.common.enums.CommonResultEnum;
import com.mouse.bms.customer.common.response.ListResult;
import com.mouse.bms.customer.common.response.PlainResult;
import com.mouse.bms.customer.common.util.ListResults;
import com.mouse.bms.customer.common.util.PlainResults;
import com.mouse.bms.customer.dal.dataobject.TagDO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : TagServiceImpl
 * @date : 2019/3/4 22:43
 * @description :
 */
@Service("tagService")
@com.alibaba.dubbo.config.annotation.Service(interfaceClass = TagService.class)
public class TagServiceImpl implements TagService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TagServiceImpl.class);

    @Resource
    private TagRepository tagRepository;

    @Override
    public PlainResult<Long> createTag(TagCreateRequest tagCreateRequest) {
        try {
            Preconditions.checkArgument(!Objects.isNull(tagCreateRequest));

            Long businessId = tagCreateRequest.getBusinessId();
            String name = tagCreateRequest.getName();

            Preconditions.checkArgument(0L < businessId && StringUtils.isNotEmpty(name));

            TagDO tagDO = tagRepository.create(buildTagDO(businessId, name, tagCreateRequest.getAuto(),
                                                          tagCreateRequest.getDescription()));
            return PlainResults.success(tagDO.getId());
        } catch (IllegalArgumentException e) {
            LOGGER.warn("TagService | createTag, params is illegal, tagCreateRequest:{}.", tagCreateRequest);
            return PlainResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (TagException e) {
            LOGGER.warn("TagService | createTag, tag already exists.");
            return PlainResults
                .error(CommonResultEnum.ERROR_DATA_EXISTS.code, CommonResultEnum.ERROR_DATA_EXISTS.message);
        } catch (Exception e) {
            LOGGER.error("TagService | createTag, exception, msg:{}.", e.getMessage());
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    @Override
    public PlainResult<Boolean> editTag(TagEditRequest tagEditRequest) {
        try {
            Preconditions.checkArgument(!Objects.isNull(tagEditRequest));

            Long businessId = tagEditRequest.getBusinessId();
            Long tagId = tagEditRequest.getTagId();
            String name = tagEditRequest.getName();
            Preconditions.checkArgument(0L < businessId && 0L < tagId && StringUtils.isNotEmpty(name));

            Boolean editTag = tagRepository.editTag(businessId, tagId, name, tagEditRequest.getAuto(),
                                                    tagEditRequest.getDescription());
            return PlainResults.success(editTag);
        } catch (IllegalArgumentException e) {
            LOGGER.warn("TagService | editTag, params is illegal, tagEditRequest:{}.", tagEditRequest);
            return PlainResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (TagException e) {
            LOGGER.warn("TagService | editTag, tag does not exists.");
            return PlainResults
                .error(CommonResultEnum.ERROR_DATA_NOT_EXISTS.code, CommonResultEnum.ERROR_DATA_NOT_EXISTS.message);
        } catch (Exception e) {
            LOGGER.error("TagService | editTag, exception, msg:{}.", e.getMessage());
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    @Override
    public PlainResult<Boolean> deleteTagById(Long businessId, Long tagId) {
        try {
            Preconditions.checkArgument(0L < businessId && 0L < tagId);
            Boolean deleteTagById = tagRepository.deleteTagById(businessId, tagId);
            return PlainResults.success(deleteTagById);
        } catch (IllegalArgumentException e) {
            LOGGER.warn("TagService | deleteTagById, params is illegal, businessId:{}, tagId:{}.", businessId, tagId);
            return PlainResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (TagException e) {
            LOGGER.warn("TagService | deleteTagById, tag does not exists.");
            return PlainResults
                .error(CommonResultEnum.ERROR_DATA_NOT_EXISTS.code, CommonResultEnum.ERROR_DATA_NOT_EXISTS.message);
        } catch (Exception e) {
            LOGGER.warn("TagService | deleteTagById, exception, msg:{}.", e.getMessage());
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    @Override
    public PlainResult<Boolean> deleteTagByName(Long businessId, String name) {
        try {
            Preconditions.checkArgument(0L < businessId && StringUtils.isNotEmpty(name));
            Boolean deleteTagByName = tagRepository.deleteTagByName(businessId, name);
            return PlainResults.success(deleteTagByName);
        } catch (IllegalArgumentException e) {
            LOGGER.warn("TagService | deleteTagByName params is illegal, businessId:{}, name:{}.", businessId, name);
            return PlainResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (TagException e) {
            LOGGER.warn("TagService | deleteTagByName, tag does not exists.");
            return PlainResults
                .error(CommonResultEnum.ERROR_DATA_NOT_EXISTS.code, CommonResultEnum.ERROR_DATA_NOT_EXISTS.message);
        } catch (Exception e) {
            LOGGER.error("TagService | deleteTagByName exception, msg:{}.", e.getMessage());
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    @Override
    public PlainResult<Boolean> deleteTagBatch(Long businessId, List<Long> tagIds) {
        try {
            Preconditions.checkArgument(0L < businessId && CollectionUtils.isNotEmpty(tagIds));

            Boolean deleteTagBatch = tagRepository.deleteTagBatch(businessId, tagIds);
            return PlainResults.success(deleteTagBatch);
        } catch (IllegalArgumentException e) {
            LOGGER.warn("TagService | deleteTagBatch params is illegal, businessId:{}, tagIds:{}.", businessId, tagIds);
            return PlainResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (TagException e) {
            LOGGER.warn("TagService | deleteTagBatch, tags does not exists.");
            return PlainResults
                .error(CommonResultEnum.ERROR_DATA_NOT_EXISTS.code, CommonResultEnum.ERROR_DATA_NOT_EXISTS.message);
        } catch (Exception e) {
            LOGGER.error("TagService | deleteTagBatch exception, msg:{}.", e.getMessage());
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    @Override
    public ListResult<TagQueryResp> listTags(TagQueryRequest tagQueryRequest) {
        try {
            Preconditions.checkArgument(!Objects.isNull(tagQueryRequest));

            Long businessId = tagQueryRequest.getBusinessId();
            Integer page = tagQueryRequest.getPage();
            Integer pageSize = tagQueryRequest.getPageSize();
            Preconditions.checkArgument(0L < businessId && 0 <= page && 0 < pageSize);

            List<TagQueryResp> tagQueryResps = tagRepository.listTags(tagQueryRequest);
            return ListResults.success(tagQueryResps);
        } catch (IllegalArgumentException e) {
            LOGGER.warn("TagService | listTags, params is illegal, tagQueryRequest:{}.", tagQueryRequest);
            return ListResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (Exception e) {
            LOGGER.error("TagService | listTags. exception, msg:{}.", e.getMessage());
            return ListResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    @Override
    public PlainResult<Integer> countTag(Long businessId, Boolean isAuto) {
        try {
            Preconditions.checkArgument(0L < businessId);
            Integer countTag = tagRepository.countTag(businessId, isAuto);
            return PlainResults.success(countTag);
        } catch (IllegalArgumentException e) {
            LOGGER.warn("TagService | countTag, params is illegal, businessId:{}, keyword:{}.", businessId, isAuto);
            return PlainResults.error(CommonResultEnum.ILLEGAL_PARAM.code, CommonResultEnum.ILLEGAL_PARAM.message);
        } catch (Exception e) {
            LOGGER.warn("TagService | countTag, exception, msg:{}.", e.getMessage());
            return PlainResults.error(CommonResultEnum.EXCEPTION.code, CommonResultEnum.EXCEPTION.message);
        }
    }

    private TagDO buildTagDO(Long businessId, String name, Boolean isAuto, String description) {
        return new TagDO()
            .setBusinessId(businessId)
            .setName(name)
            .setAuto(isAuto)
            .setDescription(description)
            .setCreatedAt(LocalDateTime.now());
    }

}
