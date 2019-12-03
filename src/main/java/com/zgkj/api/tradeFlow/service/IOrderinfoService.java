package com.zgkj.api.tradeFlow.service;

import com.zgkj.api.tradeFlow.entity.Orderinfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lucent
 * @since 2019-11-19
 */
public interface IOrderinfoService extends IService<Orderinfo> {
    List<Map<String,Object>> cntOperatorMapData(String start,String end);
    List<Map<String,Object>> cntOperatorMapData1(String start,String end);
    List<Map<String,Object>> cntOperatorMapData2(String start,String end);
}
