package com.zgkj.api.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.zgkj.api.entity.OutboundInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Lucent
 * @since 2019-11-06
 */
public interface OutboundInfoMapper extends BaseMapper<OutboundInfo> {

    @Select("SELECT OutboundInfo.OutboundGoodsID FROM OutboundInfo left join OrderInfo on OutboundInfo.SystemNumber=OrderInfo.SystemNumber WHERE OrderInfo.UserID=#{Uid} and OrderInfo.SaleDate >#{StartDate} and OrderInfo.SaleDate < #{StopDate} GROUP BY OutboundInfo.OutboundGoodsID")
    List<String> getAllId(@Param("Uid") Integer Uid,@Param("StartDate") String StartDate,@Param("StopDate") String StopDate);
}
