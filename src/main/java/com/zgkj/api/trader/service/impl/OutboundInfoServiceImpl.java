package com.zgkj.api.trader.service.impl;

import com.zgkj.api.trader.entity.OutboundInfo;
import com.zgkj.api.trader.mapper.OutboundInfoMapper;
import com.zgkj.api.trader.service.IOutboundInfoService;
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
 * @since 2019-11-06
 */
@Service
public class OutboundInfoServiceImpl extends ServiceImpl<OutboundInfoMapper, OutboundInfo> implements IOutboundInfoService {

    @Autowired
    OutboundInfoMapper outboundInfoMapper;

    @Override
    public List<String> getAllId(Integer Uid,String StartDate,String StopDate) {
        return outboundInfoMapper.getAllId(Uid,StartDate,StopDate);
    }
}
