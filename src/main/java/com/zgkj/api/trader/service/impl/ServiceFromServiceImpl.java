package com.zgkj.api.trader.service.impl;

import com.zgkj.api.trader.entity.ServiceFrom;
import com.zgkj.api.trader.mapper.ServiceFromMapper;
import com.zgkj.api.trader.service.IServiceFromService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lucent
 * @since 2019-11-13
 */
@Service
public class ServiceFromServiceImpl extends ServiceImpl<ServiceFromMapper, ServiceFrom> implements IServiceFromService {

    @Autowired
    ServiceFromMapper serviceFromMapper;

    @Override
    public List<ServiceFrom> getServiceFromList(String start, String end, String orderid){
        return serviceFromMapper.getServiceFromList(start,end,orderid);
    }

    @Override
    public List<ServiceFrom> getAppealList(String orderid){
        return serviceFromMapper.getAppealList(orderid);
    }
}
