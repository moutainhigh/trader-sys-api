package com.zgkj.api.trader.service;

import com.zgkj.api.trader.entity.OutboundInfo;
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
public interface IOutboundInfoService extends IService<OutboundInfo> {

    List<Map<String,String>> getAllId(Integer Uid, String StartDate, String StopDate);
}
