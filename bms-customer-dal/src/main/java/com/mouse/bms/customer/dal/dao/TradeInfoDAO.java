package com.mouse.bms.customer.dal.dao;

import com.mouse.bms.customer.dal.dataobject.TradeInfoDO;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : TradeInfoDAO
 * @date : 2019/3/3 21:35
 * @description :
 */
public interface TradeInfoDAO {

    TradeInfoDO get(@Param("businessId") Long businessId,
                    @Param("customerId") Long customerId);



    List<TradeInfoDO> getBatch(@Param("businessId") Long businessId,
                               @Param("customerIdS") List<Long> customerIds);


    /**
     * @return int
     */
    int insert(TradeInfoDO tradeinfoDO);

    /**
     * @param tradeinfoDO TradeinfoDO
     * @return int
     */
    int insertOnUpdate(TradeInfoDO tradeinfoDO);

    /**
     * @param businessId Long
     * @param customerId Long
     * @return List<TradeinfoDO>
     */
    List<TradeInfoDO> select(@Param("businessId") Long businessId,
                             @Param("customerId") Long customerId);

    /**
     * @param businessId Long
     * @param customerId Long
     * @param orderFrom  Integer
     * @return List<TradeinfoDO>
     */
    List<TradeInfoDO> selectByOrderFrom(@Param("businessId") Long businessId,
                                        @Param("customerId") Long customerId,
                                        @Param("orderFrom") Integer orderFrom);

    /**
     * @param businessId       Long
     * @param customerId       Long
     * @param orderFrom        Integer
     * @param orderBiz2ScrmSet Set<Integer>
     * @return List<TradeinfoDO>
     */
    List<TradeInfoDO> selectByOrderFromSetOrderBizSet(@Param("businessId") Long businessId,
                                                      @Param("customerId") Long customerId,
                                                      @Param("orderFromSet") Set<Integer> orderFrom,
                                                      @Param("orderBiz2ScrmSet") Set<Integer> orderBiz2ScrmSet);

    /**
     * @param businessId       Long
     * @param customerIds      Set<Long>
     * @param orderFrom        Set<Long>
     * @param orderBiz2ScrmSet Set<Long>
     */
    List<TradeInfoDO> listByOrderFromSetOrderBizSet(@Param("businessId") Long businessId,
                                                    @Param("customerIds") Set<Long> customerIds,
                                                    @Param("orderFromSet") Set<Integer> orderFrom,
                                                    @Param("orderBiz2ScrmSet") Set<Integer> orderBiz2ScrmSet);

    /**
     * @param TradeinfoDO TradeinfoDO
     * @return int
     */
    int update(TradeInfoDO TradeinfoDO);

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
