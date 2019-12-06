package com.zgkj.api.service;

import com.zgkj.api.entity.OrderInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lucent
 * @since 2019-11-06
 */
public interface IOrderInfoService extends IService<OrderInfo> {
    List<Map<String,Object>> getListedMapData(String start,String end,String groupIds);
    String getOrderIdWithOutboundId(String id);
}
