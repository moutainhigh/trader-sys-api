package com.zgkj.api.trader.service.impl;

import com.zgkj.api.trader.entity.OrderInfo;
import com.zgkj.api.trader.mapper.OrderInfoMapper;
import com.zgkj.api.trader.service.IOrderInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lucent
 * @since 2019-11-06
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements IOrderInfoService {

    @Autowired
    OrderInfoMapper orderInfoMapper;

    @Override
    public List<Map<String,Object>> getListedMapData(String start, String end, String groupIds){
        return orderInfoMapper.getListedMapData(start,end,groupIds);
    }
    @Override
    public String getOrderIdWithOutboundId(String id){
        return orderInfoMapper.getOrderIdWithOutboundId(id);
    }
}
