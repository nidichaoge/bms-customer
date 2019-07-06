package com.mouse.bms.customer.dal.dao;

import com.mouse.bms.customer.dal.dataobject.RefundInfoDO;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : RefundInfoDAO
 * @date : 2019/3/3 21:35
 * @description :
 */
public interface RefundInfoDAO {

    RefundInfoDO get(@Param("businessId") Long businessId,
                     @Param("customerId") Long customerId);




    /**
     * @return int
     */
    int insert(RefundInfoDO refundInfoDO);

    /**
     * @return int
     */
    int insertOnUpdate(RefundInfoDO refundInfoDO);


    /**
     * @param businessId Long
     * @param customerId Long
     * @return List<RefundInfoDO>
     */
    List<RefundInfoDO> select(@Param("businessId") Long businessId,
                              @Param("customerId") Long customerId);

    /**
     * @param businessId Long
     * @param customerId Long
     * @param orderFrom  Integer
     * @return List<RefundInfoDO>
     */
    List<RefundInfoDO> selectByOrderFrom(@Param("businessId") Long businessId,
                                         @Param("customerId") Long customerId,
                                         @Param("orderFrom") Integer orderFrom);

    /**
     * @param businessId       Long
     * @param customerId       Long
     * @param orderFrom        Integer
     * @param orderBiz2ScrmSet Set<Integer>
     * @return List<RefundInfoDO>
     */
    List<RefundInfoDO> selectByOrderFromSetOrderBizSet(@Param("businessId") Long businessId,
                                                       @Param("customerId") Long customerId,
                                                       @Param("orderFromSet") Set<Integer> orderFrom,
                                                       @Param("orderBiz2ScrmSet") Set<Integer> orderBiz2ScrmSet);

    /**
     * @param businessId       Long
     * @param customerIds      Set<Long>
     * @param orderFrom        Set<Long>
     * @param orderBiz2ScrmSet Set<Long>
     */
    List<RefundInfoDO> listByOrderFromSetOrderBizSet(@Param("businessId") Long businessId,
                                                     @Param("customerIds") Set<Long> customerIds,
                                                     @Param("orderFromSet") Set<Integer> orderFrom,
                                                     @Param("orderBiz2ScrmSet") Set<Integer> orderBiz2ScrmSet);

    /**
     * @param RefundInfoDO RefundInfoDO
     * @return int
     */
    int update(RefundInfoDO RefundInfoDO);

    /**
     * @param businessId    Long
     * @param customerId    Long
     * @param orderFrom     Integer
     * @param orderBiz2Scrm Integer
     * @param version       Integer
     * @return int
     */
    int delete(@Param("businessId") Long businessId,
               @Param("customerId") Long customerId,
               @Param("orderFrom") Integer orderFrom,
               @Param("orderBiz2Scrm") Integer orderBiz2Scrm,
               @Param("version") Integer version);

    /**
     * @param businessId Long
     * @param customerId Long
     * @return int
     */
    int clearAll(@Param("businessId") Long businessId, @Param("customerId") Long customerId);

}
