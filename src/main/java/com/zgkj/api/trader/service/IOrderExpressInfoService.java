package com.zgkj.api.trader.service;

import com.zgkj.api.express.dhl.dhlEntity.PdfInfo;
import com.zgkj.api.trader.entity.OrderExpressInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lucent
 * @since 2019-11-29
 */
public interface IOrderExpressInfoService extends IService<OrderExpressInfo> {

    /**
     * @param orderId
     * @param pdfInfo
     * @param userId
     * @param request
     * @return
     */
    Boolean changeExpress(String orderId, String TrackingNum,String ShippineType,Integer expressAge,Integer userId, HttpServletRequest request);
}
