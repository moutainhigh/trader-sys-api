package com.zgkj.api.trader.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zgkj.api.express.dhl.dhlEntity.PdfInfo;
import com.zgkj.api.trader.entity.OrderExpressInfo;
import com.zgkj.api.trader.entity.OrderRecord;
import com.zgkj.api.trader.mapper.OrderExpressInfoMapper;
import com.zgkj.api.trader.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zgkj.util.DateUtils;
import com.zgkj.util.IPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lucent
 * @since 2019-11-29
 */
@Service
public class OrderExpressInfoServiceImpl extends ServiceImpl<OrderExpressInfoMapper, OrderExpressInfo> implements IOrderExpressInfoService {

    @Autowired
    IOrderInfoService orderInfoService;
    @Autowired
    IOutboundInfoService outboundInfoService;
    @Autowired
    IRegionInfoService regionInfoService;
    @Autowired
    IProductClassService productClassService;
    @Autowired
    IOrderExpressInfoService orderExpressInfoService;
    @Autowired
    IOrderRecordService orderRecordService;

    @Override
    public Boolean changeExpress(String orderId, String TrackingNum,String ShippineType,Integer expressAge, Integer userId, HttpServletRequest request){
        QueryWrapper<OrderExpressInfo> orderExpressInfoQueryWrapper=new QueryWrapper<>();
        orderExpressInfoQueryWrapper.eq("OrderID",orderId);
        OrderExpressInfo orderExpressInfo=orderExpressInfoService.getOne(orderExpressInfoQueryWrapper);
        Boolean flag=false;
        if (null!=orderExpressInfo){
            orderExpressInfo.setTheExpressNo(TrackingNum);
            orderExpressInfo.setExpressage(expressAge);
            orderExpressInfo.setTypeOfShipping(ShippineType);
            orderExpressInfo.setCreateDate(DateUtils.getTimeNow());
            flag= orderExpressInfoService.update(orderExpressInfo,orderExpressInfoQueryWrapper);
        }else {
            orderExpressInfo=new OrderExpressInfo();
            orderExpressInfo.setTheExpressNo(TrackingNum);
            orderExpressInfo.setExpressage(expressAge);
            orderExpressInfo.setTypeOfShipping(ShippineType);
            orderExpressInfo.setOrderID(orderId);
            orderExpressInfo.setCreateDate(DateUtils.getTimeNow());
            flag= orderExpressInfoService.save(orderExpressInfo);
        }
        OrderRecord orderRecord=new OrderRecord();
        orderRecord.setUserID(userId);
        orderRecord.setOrderID(orderId);
        orderRecord.setOperateContent("将运单号修改为: "+TrackingNum);
        orderRecord.setIp(IPUtil.getClientIP(request));
        orderRecord.setStatus(0);
        orderRecord.setCreateDate(DateUtils.getTimeNow());
        orderRecordService.save(orderRecord);
        return flag;
    }

}
