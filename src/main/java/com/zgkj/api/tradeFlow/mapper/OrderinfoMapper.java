package com.zgkj.api.tradeFlow.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.zgkj.api.tradeFlow.entity.Orderinfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Lucent
 * @since 2019-11-19
 */
@DS("tradeFlow")
public interface OrderinfoMapper extends BaseMapper<Orderinfo> {
    List<Map<String,Object>> cntOperatorMapData(@Param("start")String start,@Param("end")String end);
    List<Map<String,Object>> cntOperatorMapData1(@Param("start")String start,@Param("end")String end);
    List<Map<String,Object>> cntOperatorMapData2(@Param("start")String start,@Param("end")String end);
}
