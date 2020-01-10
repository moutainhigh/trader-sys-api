package com.zgkj.api.trader.service;

import com.zgkj.api.shops.aliExpress.entity.Order;
import com.zgkj.api.trader.entity.OrderInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zgkj.api.trader.entity.ShopConfig;

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

    /**
     *
     * 速卖通录单
     * 开始时间和结束时间为null时，时间范围默认为一天前到当前时间
     */
    void importAliExpressOrders(ShopConfig shop,String timeStart,String timeEnd) throws Exception;

    /**
     * 速卖通自动录单
     */
    void autoImportAliExpressOrders();
    Boolean isPending(OrderInfo orderInfo,ShopConfig shopConfig) throws Exception;
    Boolean pendingExist(Order order, Integer shopId) throws Exception;
    Boolean orderInfoExist(Order order, List<OrderInfo> oldOrders);
    OrderInfo makeOrderInfo(Order order,ShopConfig shopConfig) throws Exception;
    void makeLog(String orderId,String log);
}
