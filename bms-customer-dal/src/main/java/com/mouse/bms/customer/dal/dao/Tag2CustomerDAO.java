package com.mouse.bms.customer.dal.dao;

import com.mouse.bms.customer.dal.dataobject.Tag2CustomerDO;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : Tag2CustomerDAO
 * @date : 2019/3/3 21:36
 * @description :
 */
public interface Tag2CustomerDAO {

    long insert(Tag2CustomerDO tag2CustomerDO);

    boolean remove(@Param("businessId") Long businessId,
                   @Param("tagId") Long tagId,
                   @Param("customerId") Long customerId);


    Set<Tag2CustomerDO> getCustomerIdsByTagId(@Param("tagId") Long tagId,
                                              @Param("offset") Integer offset,
                                              @Param("pageSize") Integer pageSize);

    Tag2CustomerDO get(@Param("tagId") Long tagId,
                       @Param("customerId") Long customerId);

    boolean updateSourceBusinessSideId(@Param("customerId") Long customerId,
                                       @Param("tagId") Long tagId,
                                       @Param("sourceBusinessSideId") Long sourceBusinessSideId);

    long addBatch(List<Tag2CustomerDO> tags);

}
