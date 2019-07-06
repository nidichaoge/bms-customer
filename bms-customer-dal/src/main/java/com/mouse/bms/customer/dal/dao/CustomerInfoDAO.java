package com.mouse.bms.customer.dal.dao;

import com.mouse.bms.customer.dal.dataobject.CustomerDO;
import com.mouse.bms.customer.dal.dataobject.CustomerInfoDO;
import com.mouse.bms.customer.dal.dataobject.TagDO;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : CustomerInfoDAO
 * @date : 2019/3/3 21:37
 * @description :
 */
//@Repository
public interface CustomerInfoDAO {

    long insert(CustomerInfoDO customerInfoDO);
    
    boolean delete(@Param("businessId")Long businessId,
                   @Param("customerId")Long customerId);

    boolean update(@Param("businessId")Long businessId,
                   @Param("customerId")Long customerId,
                   @Param("area")String area,
                   @Param("weChat")String weChat,
                   @Param("birthday") LocalDateTime birthday,
                   @Param("anniversary") LocalDateTime anniversary);

    CustomerInfoDO get(@Param("businessId")Long businessId,
                       @Param("customerId")Long customerId);



    int getCount(@Param("businessId")Long businessId,
                 @Param("includeDeleted")Short includeDeleted,
                 @Param("keyword")String keyword,
                 @Param("isAuto")Boolean isAuto);

    List<TagDO> getTags(@Param("businessId")Long businessId);

    Set<TagDO> getBatch(@Param("businessId")Long businessId,
                        @Param("customerIds") Set<Long> customerIds);

    TagDO getByName(@Param("businessId")Long businessId,
                    @Param("name")String name);

    Set<TagDO> getByNameBatch(@Param("businessId")Long businessId,
                              @Param("names")Set<String> names);

    List<TagDO> getList(@Param("businessId")Long businessId,
                        @Param("keyword")String keyword,
                        @Param("offset")Integer offset,
                        @Param("pageSize")Integer pageSize,
                        @Param("order")String order,
                        @Param("orderBy")String orderBy,
                        @Param("isAuto")Boolean isAuto);

    boolean plusCustomerNum(@Param("businessId")Long businessId,
                            @Param("customerId")Long customerId,
                            @Param("plus")Integer plus);

    boolean subtractCustomerNum(@Param("businessId")Long businessId,
                                @Param("customerId")Long customerId,
                                @Param("subtract")Integer subtract);

    boolean updateIsAuto(@Param("businessId")Long businessId,
                         @Param("customerId")Long customerId,
                         @Param("isAuto")Boolean isAuto);

}
