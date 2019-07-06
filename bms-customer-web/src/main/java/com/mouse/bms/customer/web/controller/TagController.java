package com.mouse.bms.customer.web.controller;

import com.alibaba.fastjson.JSON;
import com.mouse.bms.customer.api.request.TagCreateRequest;
import com.mouse.bms.customer.api.request.TagEditRequest;
import com.mouse.bms.customer.api.request.TagQueryRequest;
import com.mouse.bms.customer.api.response.TagQueryResp;
import com.mouse.bms.customer.api.service.TagService;
import com.mouse.bms.customer.common.response.ListResult;
import com.mouse.bms.customer.common.response.PlainResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.annotation.Resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : TagController
 * @date : 2019/3/4 23:15
 * @description :
 */
@RestController
@RequestMapping("/tag")
@Api(value = "标签管理",tags = "标签入口")
public class TagController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TagController.class);

    @Resource
    private TagService tagService;

    @PostMapping("/create")
    @ApiOperation(value = "创建标签",httpMethod = "POST")
    public PlainResult<Long> createTag(@RequestBody TagCreateRequest tagCreateRequest) {
        LOGGER.info("TagController | createTag, tagCreateRequest:{}.", tagCreateRequest);
        return tagService.createTag(tagCreateRequest);
    }

    @PutMapping("/edit")
    @ApiOperation(value = "编辑标签")
    public PlainResult<Boolean> editTag(@RequestBody TagEditRequest tagEditRequest) {
        LOGGER.info("TagController | editTag, tagEditRequest:{}.", tagEditRequest);
        return tagService.editTag(tagEditRequest);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "根据ID删除标签")
    public PlainResult<Boolean> deleteTagById(@RequestParam Long businessId, @RequestParam Long tagId) {
        LOGGER.info("TagController | deleteTagById, businessId:{}, tagId:{}.", businessId, tagId);
        return tagService.deleteTagById(businessId, tagId);
    }

    @DeleteMapping("/deleteByName")
    @ApiOperation(value = "根据名称删除标签")
    public PlainResult<Boolean> deleteTagByName(@RequestParam Long businessId, @RequestParam String name) {
        LOGGER.info("TagController | deleteTagByName, businessId:{}, name:{}.", businessId, name);
        return tagService.deleteTagByName(businessId, name);
    }

    @DeleteMapping("/deleteBatch")
    @ApiOperation(value = "批量删除标签")
    public PlainResult<Boolean> deleteTagBatch(@RequestParam Long businessId, @RequestParam List<Long> tagIds) {
        LOGGER.info("TagController | deleteTagBatch, businessId:{}, tagIds:{}.", businessId, tagIds);
        return tagService.deleteTagBatch(businessId, tagIds);
    }

    @GetMapping("/list")
    @ApiOperation(value = "标签列表")
    public ListResult<TagQueryResp> listTags(@RequestParam String query) {
        TagQueryRequest tagQueryRequest = JSON.parseObject(query, TagQueryRequest.class);
        LOGGER.info("TagController | listTags, tagQueryRequest:{}.", tagQueryRequest);
        return tagService.listTags(tagQueryRequest);
    }

    @GetMapping("/count")
    @ApiOperation(value = "标签数量")
    public PlainResult<Integer> countTag(@RequestParam Long businessId, @RequestParam Boolean isAuto) {
        LOGGER.info("TagController | countTag, businessId:{}, isAuto:{}.", businessId, isAuto);
        return tagService.countTag(businessId, isAuto);
    }

}
