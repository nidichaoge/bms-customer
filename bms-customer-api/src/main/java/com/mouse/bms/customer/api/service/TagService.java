package com.mouse.bms.customer.api.service;

import com.mouse.bms.customer.api.request.TagCreateRequest;
import com.mouse.bms.customer.api.request.TagEditRequest;
import com.mouse.bms.customer.api.request.TagQueryRequest;
import com.mouse.bms.customer.api.response.TagQueryResp;
import com.mouse.bms.customer.common.response.ListResult;
import com.mouse.bms.customer.common.response.PlainResult;

import java.util.List;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : TagService
 * @date : 2019/3/2 15:11
 * @description : 标签服务
 */
public interface TagService {

    /**
     * 创建标签
     * @param tagCreateRequest
     * @return
     */
    PlainResult<Long> createTag(TagCreateRequest tagCreateRequest);

    /**
     * 编辑标签
     * @param tagEditRequest
     * @return
     */
    PlainResult<Boolean> editTag(TagEditRequest tagEditRequest);

    /**
     * 根据ID删除标签
     * @param businessId
     * @param tagId
     * @return
     */
    PlainResult<Boolean> deleteTagById(Long businessId, Long tagId);

    /**
     * 根据名字删除标签
     * @param businessId
     * @param name
     * @return
     */
    PlainResult<Boolean> deleteTagByName(Long businessId, String name);

    /**
     * 批量删除标签
     * @param businessId
     * @param tagIds
     * @return
     */
    PlainResult<Boolean> deleteTagBatch(Long businessId, List<Long> tagIds);

    /**
     * 查询标签
     * @param tagQueryRequest
     * @return
     */
    ListResult<TagQueryResp> listTags(TagQueryRequest tagQueryRequest);

    /**
     * 标签数量
     * @param businessId
     * @param auto
     * @return
     */
    PlainResult<Integer> countTag(Long businessId,  Boolean auto);

}
