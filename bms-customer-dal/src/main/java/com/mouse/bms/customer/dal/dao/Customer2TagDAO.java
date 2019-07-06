package com.mouse.bms.customer.dal.dao;

import com.mouse.bms.customer.dal.dataobject.Customer2TagDO;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : Customer2TagDAO
 * @date : 2019/3/3 21:36
 * @description :
 */
public interface Customer2TagDAO {

    long insert(Customer2TagDO customer2TagDO);

    boolean removeTagBatch(@Param("businessId") Long businessId,
                           @Param("customerId") Long customerId,
                           @Param("tagIds") Set<Long> tagIds);


    Set<Customer2TagDO> getCustomerTagIds(@Param("businessId") Long businessId,
                                          @Param("customerId") Long customerId);

    Set<Customer2TagDO> getBatch(@Param("businessId") Long businessId,
                                 @Param("customerId") Long customerId,
                                 @Param("tagIds") Set<Long> tagIds);


    Customer2TagDO get(@Param("businessId") Long businessId,
                       @Param("customerId") Long customerId,
                       @Param("tagId") Long tagId);

    boolean updateSourceBusinessSideId(@Param("businessId") Long businessId,
                                       @Param("customerId") Long customerId,
                                       @Param("tagId") Long tagId,
                                       @Param("sourceBusinessSideId") Long sourceBusinessSideId);

    List<Customer2TagDO> getByCustomerIdWithLock(@Param("businessId") Long businessId,
                                                 @Param("customerId") Long customerId);

    long addBatch(List<Customer2TagDO> tags);

}
