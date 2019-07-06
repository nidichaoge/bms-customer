package com.mouse.bms.customer.dal.dao;

import com.mouse.bms.customer.dal.dataobject.CustomerDO;
import com.mouse.bms.customer.dal.dataobject.TagDO;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : CustomerDAO
 * @date : 2019/3/3 21:37
 * @description :
 */
public interface CustomerDAO {

    long insert(CustomerDO customerDO);

    CustomerDO get(@Param("businessId") Long businessId,
                   @Param("customerId") Long customerId);

    CustomerDO getByMobile(@Param("businessId") Long businessId,
                           @Param("mobile") String mobile);

    boolean update(@Param("businessId") Long businessId,
                   @Param("customerId") Long customerId,
                   @Param("nickName") String nickName,
                   @Param("mobile") String mobile,
                   @Param("gender") Short gender,
                   @Param("description") String description);

    List<CustomerDO> list(@Param("businessId") Long businessId,
                          @Param("keyword") String keyword,
                          @Param("offset") Integer offset,
                          @Param("pageSize") Integer pageSize,
                          @Param("order") String order,
                          @Param("orderBy") String orderBy);

    Set<CustomerDO> getBatch(@Param("businessId") Long businessId,
                             @Param("customerIds") Set<Long> customerIds);


    boolean delete(@Param("businessId") Long businessId,
                   @Param("customerId") Long customerId);

    long getCount(@Param("businessId") Long businessId,
                  @Param("includeDeleted") Short includeDeleted);

    List<CustomerDO> getTags(@Param("businessId") Long businessId);


    CustomerDO getByName(@Param("businessId") Long businessId,
                         @Param("name") String name);

    Set<CustomerDO> getByNameBatch(@Param("businessId") Long businessId,
                                   @Param("names") Set<String> names);

    boolean plusCustomerNum(@Param("businessId") Long businessId,
                            @Param("customerId") Long customerId,
                            @Param("plus") Integer plus);

    boolean subtractCustomerNum(@Param("businessId") Long businessId,
                                @Param("customerId") Long customerId,
                                @Param("subtract") Integer subtract);

    boolean updateIsAuto(@Param("businessId") Long businessId,
                         @Param("customerId") Long customerId,
                         @Param("isAuto") Boolean isAuto);
}
