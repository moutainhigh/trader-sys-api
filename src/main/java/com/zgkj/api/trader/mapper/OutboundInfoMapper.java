package com.zgkj.api.trader.mapper;

import com.zgkj.api.trader.entity.OutboundInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Lucent
 * @since 2019-11-06
 */
public interface OutboundInfoMapper extends BaseMapper<OutboundInfo> {

    List<Map<String,String>> getAllId(@Param("Uid") Integer Uid, @Param("StartDate") String StartDate, @Param("StopDate") String StopDate);
}
