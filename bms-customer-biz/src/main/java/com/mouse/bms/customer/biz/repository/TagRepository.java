package com.mouse.bms.customer.biz.repository;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mouse.bms.customer.api.request.TagQueryRequest;
import com.mouse.bms.customer.api.response.TagQueryResp;
import com.mouse.bms.customer.biz.exception.TagException;
import com.mouse.bms.customer.dal.dao.TagDAO;
import com.mouse.bms.customer.dal.dataobject.TagDO;

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
 * @fileName : TagRepository
 * @date : 2019/3/18 15:41
 * @description :
 */
@Component("tagRepository")
public class TagRepository {

    private static final String ORDER = "desc";
    private static final String ORDER_BY = "created_at";

    @Resource
    private TagDAO tagDAO;

    public static void main(String[] args) {
        System.out.println(new Date());
        System.out.println(LocalDateTime.now());
    }

    public TagDO create(TagDO tagDO) throws Exception {
        Preconditions.checkArgument(!Objects.isNull(tagDO));
        Long businessId = tagDO.getBusinessId();
        String name = tagDO.getName();
        Preconditions.checkArgument(0L < businessId && StringUtils.isNotEmpty(name));

        TagDO exist = tagDAO.getByName(businessId, name);
        if (!Objects.isNull(exist)) {
            throw new TagException("tag already exists");
        }
        System.out.println(tagDO);
        long insert = tagDAO.insert(tagDO);
        System.out.println(tagDO);
        return tagDO;
    }

    public Boolean editTag(Long businessId, Long tagId, String name, Boolean isAuto, String description)
        throws Exception {
        Preconditions.checkArgument(0L < businessId && 0L < tagId && StringUtils.isNotEmpty(name));

        TagDO tagDO = tagDAO.get(businessId, tagId);
        if (Objects.isNull(tagDO)) {
            throw new TagException("tag does not exists");
        }
        return tagDAO.update(businessId, tagId, name, tagDO.getCustomerNum(), isAuto, description);
    }

    public Boolean deleteTagById(Long businessId, Long tagId) throws Exception {
        Preconditions.checkArgument(0L < businessId && 0L < tagId);

        TagDO tagDO = tagDAO.get(businessId, tagId);
        if (Objects.isNull(tagDO)) {
            throw new TagException("tag does not exists");
        }
        return tagDAO.delete(businessId, tagId);

    }

    public Boolean deleteTagByName(Long businessId, String name) throws Exception {
        Preconditions.checkArgument(0L < businessId && StringUtils.isNotEmpty(name));

        TagDO tagDO = tagDAO.getByName(businessId, name);
        if (Objects.isNull(tagDO)) {
            throw new TagException("tag does not exists");
        }
        return tagDAO.delete(businessId, tagDO.getId());
    }

    public Boolean deleteTagBatch(Long businessId, List<Long> tagIds) throws Exception {
        Preconditions.checkArgument(0L < businessId && CollectionUtils.isNotEmpty(tagIds));

        Set<TagDO> tagDOS = tagDAO.getBatch(businessId, Sets.newHashSet(tagIds));
        if (CollectionUtils.isEmpty(tagDOS)) {
            throw new TagException("tags does not exists");
        }
        tagDOS.forEach(tagDO -> tagDAO.delete(businessId, tagDO.getId()));
        return Boolean.TRUE;
    }

    public List<TagQueryResp> listTags(TagQueryRequest tagQueryRequest) throws Exception {
        Preconditions.checkArgument(!Objects.isNull(tagQueryRequest));
        Long businessId = tagQueryRequest.getBusinessId();
        Integer page = tagQueryRequest.getPage();
        Integer pageSize = tagQueryRequest.getPageSize();
        Preconditions.checkArgument(0L < businessId && 0 <= page && 0 < pageSize);

        setParam(tagQueryRequest);

        List<TagDO> tagDOS = tagDAO.list(businessId, tagQueryRequest.getKeyword(), page*pageSize, pageSize,
                                         tagQueryRequest.getOrder(), tagQueryRequest.getOrderBy(),
                                         tagQueryRequest.getAuto());
        if (CollectionUtils.isEmpty(tagDOS)) {
            return Lists.newArrayList();
        }
        return tagDOS.stream().filter(Objects::nonNull).map(this::buildTagDO).filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    public Integer countTag(Long businessId, Boolean auto) throws Exception {
        Preconditions.checkArgument(0L < businessId);
        return tagDAO.getCount(businessId, (short) 0, null, auto);
    }

    public TagDO get(Long businessId, Long tagId) {
        Preconditions.checkArgument(0L < businessId && 0L < tagId);
        return tagDAO.get(businessId, tagId);
    }

    public Set<TagDO> getBatch(Long businessId, Set<Long> tagIds) {
        Preconditions.checkArgument(0L < businessId && CollectionUtils.isNotEmpty(tagIds));
        return tagDAO.getBatch(businessId, tagIds);
    }

    public Boolean plusTagCustomerNum(Long businessId, Long tagId, Long num) throws Exception {
        Preconditions.checkArgument(0L < businessId && 0L < tagId && 0L < num);

        TagDO tagDO = tagDAO.get(businessId, tagId);
        if (Objects.isNull(tagDO)) {
            throw new TagException("tag does not exists");
        }
        return tagDAO.plusCustomerNum(businessId, tagId, num);
    }

    public Boolean subtractTagCustomerNum(Long businessId, Long tagId, Long num) throws Exception {
        Preconditions.checkArgument(0L < businessId && 0L < tagId && 0L < num);

        TagDO tagDO = tagDAO.get(businessId, tagId);
        if (Objects.isNull(tagDO)) {
            throw new TagException("tag does not exists");
        }
        return tagDAO.subtractCustomerNum(businessId, tagId, num);
    }

    private TagQueryResp buildTagDO(TagDO tagDO) {
        return new TagQueryResp()
            .setTagId(tagDO.getId())
            .setName(tagDO.getName())
            .setCustomerNum(tagDO.getCustomerNum())
            .setCreatedAt(tagDO.getCreatedAt())
            .setDescription(tagDO.getDescription());
    }

    private void setParam(TagQueryRequest tagQueryRequest) {
        tagQueryRequest.setKeyword("".equals(tagQueryRequest.getKeyword()) ? null : tagQueryRequest.getKeyword());
        tagQueryRequest.setOrder(StringUtils.isEmpty(tagQueryRequest.getOrder()) ? ORDER : tagQueryRequest.getOrder());
        tagQueryRequest
            .setOrderBy(StringUtils.isEmpty(tagQueryRequest.getOrderBy()) ? ORDER_BY : tagQueryRequest.getOrderBy());
    }

}
