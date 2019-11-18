package com.zgkj.api.mapper;

import com.zgkj.api.entity.OrderInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

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
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {

    List<Map<String,Object>> getListedMapData(String start,String end,String groupIds);

}
