package com.mouse.bms.customer.dal.dao;

import com.mouse.bms.customer.dal.dataobject.TagDO;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : TagDAO
 * @date : 2019/3/2 15:55
 * @description :
 */
public interface TagDAO {

    long insert(TagDO tagDO);

    boolean delete(@Param("businessId") Long businessId,
                   @Param("tagId") Long tagId);

    boolean update(@Param("businessId") Long businessId,
                   @Param("tagId") Long tagId,
                   @Param("name") String name,
                   @Param("customerNum") Long customerNum,
                   @Param("auto") Boolean auto,
                   @Param("description") String description);

    TagDO get(@Param("businessId") Long businessId,
              @Param("tagId") Long tagId);

    int getCount(@Param("businessId") Long businessId,
                 @Param("includeDeleted") Short includeDeleted,
                 @Param("keyword") String keyword,
                 @Param("auto") Boolean auto);

    List<TagDO> getAll(@Param("businessId") Long businessId);

    Set<TagDO> getBatch(@Param("businessId") Long businessId,
                        @Param("tagIds") Set<Long> tagIds);

    TagDO getByName(@Param("businessId") Long businessId,
                    @Param("name") String name);

    Set<TagDO> getByNameBatch(@Param("businessId") Long businessId,
                              @Param("names") Set<String> names);

    List<TagDO> list(@Param("businessId") Long businessId,
                     @Param("keyword") String keyword,
                     @Param("offset") Integer offset,
                     @Param("pageSize") Integer pageSize,
                     @Param("order") String order,
                     @Param("orderBy") String orderBy,
                     @Param("auto") Boolean auto);

    boolean plusCustomerNum(@Param("businessId") Long businessId,
                            @Param("tagId") Long tagId,
                            @Param("plus") Long plus);

    boolean subtractCustomerNum(@Param("businessId") Long businessId,
                                @Param("tagId") Long tagId,
                                @Param("subtract") Long subtract);

    boolean updateIsAuto(@Param("businessId") Long businessId,
                         @Param("tagId") Long tagId,
                         @Param("auto") Boolean auto);

}
