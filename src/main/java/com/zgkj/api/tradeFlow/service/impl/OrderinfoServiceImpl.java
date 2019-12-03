package com.zgkj.api.tradeFlow.service.impl;

import com.zgkj.api.tradeFlow.entity.Orderinfo;
import com.zgkj.api.tradeFlow.mapper.OrderinfoMapper;
import com.zgkj.api.tradeFlow.service.IOrderinfoService;
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
 * @since 2019-11-19
 */
@Service
public class OrderinfoServiceImpl extends ServiceImpl<OrderinfoMapper, Orderinfo> implements IOrderinfoService {

    @Autowired
    OrderinfoMapper orderinfoMapper;

    @Override
    public List<Map<String,Object>> cntOperatorMapData(String start,String end){
        return orderinfoMapper.cntOperatorMapData(start,end);
    }

    @Override
    public List<Map<String,Object>> cntOperatorMapData1(String start,String end){
        return orderinfoMapper.cntOperatorMapData1(start, end);
    }

    @Override
    public List<Map<String,Object>> cntOperatorMapData2(String start,String end){
        return orderinfoMapper.cntOperatorMapData2(start, end);
    }
}
