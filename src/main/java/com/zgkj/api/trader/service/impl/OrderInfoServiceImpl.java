package com.zgkj.api.trader.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zgkj.api.shops.aliExpress.entity.Goods;
import com.zgkj.api.shops.aliExpress.entity.Order;
import com.zgkj.api.trader.entity.*;
import com.zgkj.api.trader.mapper.OrderInfoMapper;
import com.zgkj.api.trader.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zgkj.util.CommonUtils;
import com.zgkj.util.DateUtils;
import com.zgkj.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
@Slf4j
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements IOrderInfoService {

    @Autowired
    OrderInfoMapper orderInfoMapper;
    @Autowired
    IShopConfigService shopConfigService;
    @Autowired
    IRegionInfoService regionInfoService;
    @Autowired
    IProductInfoService productInfoService;
    @Autowired
    IPendingOrderService pendingOrderService;
    @Autowired
    IPendingOutBoundService pendingOutBoundService;
    @Autowired
    IOutboundInfoService outboundInfoService;
    @Autowired
    IOrderInfoService orderInfoService;
    @Autowired
    IOrderInfoMemoService memoService;
    @Autowired
    IOrderRecordService orderRecordService;

    private static String API_URL="http://merchantapi.sdsdiy.com/gateway/order_import_record_orders/order_previews?";
    private static String APPSECRET="kzJrBwwlnhGpZmiI16QgqQevZyUpVrwDXXpFKnDZfuxd3FDVGi0rsj26yJvBhoS7Ls5bvkgpTIoMgSBFDIhw9Cn9kCoSfbwimi9V8WqQdytlaKihLqTDDad0DXqHVQcN";
    private static String APPID="mLHp6EmSTyQ7nIlEQDbelJAObRc8k8Iw";
    private static String IMG_UPLOAD_URL="http://161.189.12.212:8012/NewTO/imgService.jsp?imgUrl=";
    private static String A_LI_EXPRESS_URL="https://es.aliexpress.com/item/";
    private static String CONTAIN_FLAG="items";


    @Override
    public List<Map<String,Object>> getListedMapData(String start, String end, String groupIds){
        return orderInfoMapper.getListedMapData(start,end,groupIds);
    }
    @Override
    public String getOrderIdWithOutboundId(String id){
        return orderInfoMapper.getOrderIdWithOutboundId(id);
    }

    @Override
    @Transactional(value="transactionManager",rollbackFor = Exception.class)
    public void autoImportAliExpressOrders(){
        log.info("-----------------速卖通导单开始: "+ DateUtils.getTimeNow()+"-----------------");
        QueryWrapper<ShopConfig> wrapper=new QueryWrapper<>();
        wrapper.eq("platform",4);
        List<ShopConfig> list=shopConfigService.list(wrapper);
        for (ShopConfig shop:list) {
            try {
                if (shop.getShopKey().startsWith("cn")){
                    importAliExpressOrders(shop,null,null);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        log.info("-----------------速卖通导单结束: "+ DateUtils.getTimeNow()+"-----------------");
    }

    @Override
    @Transactional(value="transactionManager",rollbackFor = Exception.class)
    public void importAliExpressOrders(ShopConfig shop,String timeStart,String timeEnd) throws Exception{
        String time=(new Date()).getTime()+"";
        String timeNow=DateUtils.getTimeNow();
        String timeyestarday=DateUtils.getTimeBefore(1);
        if (null!=timeStart&&null!=timeEnd){
            timeNow=timeStart;
            timeyestarday=timeEnd;
        }
        String urlParam="seller_id=" +shop.getShopKey() +"&gmt_created_begin=" +timeyestarday +"&gmt_created_end=" +timeNow + "&_timestamp=" +time +"&_appid=" +APPID+"&_sign=";
        String encript="_appid=" +APPID +"&_appsecret=" +APPSECRET +"&_timestamp=" +time +"&gmt_created_begin=" +timeyestarday +"&gmt_created_end=" +timeNow+"&seller_id=" +shop.getShopKey();
        String sign="";
        try {
            sign= CommonUtils.encryptMD5(encript);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String url=API_URL+urlParam.replace(" ","%20")+sign;
        try {
            String re= HttpUtil.sendGet(url);
            if (re.contains(CONTAIN_FLAG)){
                JSONObject jsonObject= JSON.parseObject(re);
                List<Order> orders= JSONArray.parseArray(jsonObject.get("items").toString(),Order.class);
                log.info(shop.getShopKey()+" 录单区间: "+timeyestarday+"至"+timeNow+" 订单数量: "+orders.size()+" 接口链接: "+url);
                List<OrderInfo> newOrders=new ArrayList<>();
                List<OrderInfo> oldOrders=new ArrayList<>();
                if (orders!=null&&!"".equals(orders)){
                    for (Order order :orders) {
                        if (!orderInfoService.orderInfoExist(order,oldOrders)&&!orderInfoService.pendingExist(order,shop.getShopID())){
                            newOrders.add(orderInfoService.makeOrderInfo(order,shop));
                        }
                    }
                    log.info("新订单数: "+newOrders.size()+" 旧订单数: "+oldOrders.size());
                    if (!oldOrders.isEmpty()){
                        for (OrderInfo orderInfo:oldOrders) {
                            QueryWrapper<OrderInfo> wrapper=new QueryWrapper<>();
                            wrapper.eq("OrderID",orderInfo.getOrderID());
                            Boolean i=orderInfoService.update(orderInfo,wrapper);
                            if (i){
                                log.info("更新订单："+orderInfo.getOrderID()+" 成功!");
                            }
                        }
                    }
                    for (OrderInfo orderInfo :newOrders) {
                        if (!orderInfoService.isPending(orderInfo,shop)){
                            Boolean i =orderInfoService.save(orderInfo);
                            Boolean j=true;
                            if (i){
                                OrderInfoMemo memo=orderInfo.getMemo();
                                if (null!=memo){
                                    memoService.save(memo);
                                }
                                for (OutboundInfo outboundInfo:orderInfo.getOutboundInfos()) {
                                    j=j&outboundInfoService.save(outboundInfo);
                                }
                            }
                            if (i&j){
                                orderInfoService.makeLog(orderInfo.getOrderID(),"自动录入订单");
                                log.info("录单成功："+orderInfo.getOrderID());
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        shop.setGetOrderTime(timeNow);
        QueryWrapper<ShopConfig> shopConfigQueryWrapper=new QueryWrapper<>();
        shopConfigQueryWrapper.eq("ShopKey",shop.getShopKey());
        shopConfigService.update(shop,shopConfigQueryWrapper);
    }

    /**
     * 封装orderinfo
     * @param order
     * @param shopConfig
     * @return
     */
    @Override
    public OrderInfo makeOrderInfo(Order order, ShopConfig shopConfig) throws Exception{
        String now=DateUtils.getTimeNow();
        String sysNum=DateUtils.getSystemNumber();
        OrderInfo orderInfo=new OrderInfo();
        orderInfo.setUserID(shopConfig.getUserID());
        orderInfo.setAmazonStatus("");
        orderInfo.setISPrior(0);
        orderInfo.setCurrencyCode("USD");
        orderInfo.setPlatform("4");
        orderInfo.setWareHouseID(1);
        orderInfo.setOrderState(0);
        orderInfo.setSystemNumber(sysNum);
        orderInfo.setSaleDate(now);
        orderInfo.setStopDate(now);
        orderInfo.setAddressee(order.getAddress().getConsignee());
        orderInfo.setAddress(order.getAddress().getDetail());
        QueryWrapper<RegionInfo> wrapper=new QueryWrapper<>();
        wrapper.eq("RegionAbbr",order.getAddress().getCountry());
        wrapper.eq("ParentID",0);
        RegionInfo regionInfo=regionInfoService.getOne(wrapper);
        orderInfo.setCountry(regionInfo.getRegionEghName());
        orderInfo.setProvince(order.getAddress().getProvince());
        orderInfo.setCity(order.getAddress().getCity());
        orderInfo.setZipCode(order.getAddress().getPostcode());
        orderInfo.setPhone(order.getAddress().getMobile_phone());
        orderInfo.setShopID(shopConfig.getShopID());
        orderInfo.setOrderID(order.getOut_order_no());
        orderInfo.setTotalPrice(order.getAmount().toString());
        orderInfo.setOrderRemarks(0);
        OrderInfoMemo memo=new OrderInfoMemo();
        memo.setOrderId(order.getOut_order_no());
        memo.setOrderMemo(order.getRemark());
        memo.setUpdateDate(now);
        orderInfo.setMemo(memo);
        List<OutboundInfo> list=new ArrayList<>();
        for (Goods goods :order.getItems()) {
            OutboundInfo outboundInfo=new OutboundInfo();
            outboundInfo.setOrderItemID("0");
            outboundInfo.setProductName(goods.getProduct_name());
            outboundInfo.setOutboundGoodsID("0");
            outboundInfo.setOutboundNumder(goods.getNum());
            outboundInfo.setASINYard(goods.getProduct_sku().replace(" ","-"));
            outboundInfo.setSystemNumber(sysNum);
            outboundInfo.setPrice(goods.getAmount().toString());
            DecimalFormat df = new DecimalFormat("#.00");
            outboundInfo.setThePricePaid(String.valueOf(df.format(goods.getAmount()*0.95)));
            outboundInfo.setOutState(0);
            outboundInfo.setAmazonPrice(goods.getAmount().toString());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            outboundInfo.setOutboundTime(simpleDateFormat.parse(DateUtils.getTimeNow()));
            outboundInfo.setProductAddress(A_LI_EXPRESS_URL+goods.getProduct_sku().split("_")[0]+".html");
            outboundInfo.setTheImagePath(goods.getImg_urls().get(0));
            outboundInfo.setRemarks("");
            List<ProductInfo> productInfos=productInfoService.getProductInfo(outboundInfo.getASINYard());
            if (!productInfos.isEmpty()){
                ProductInfo productInfo=productInfos.get(0);
                outboundInfo.setProductClass(productInfo.getClassifyName());
                outboundInfo.setASINYard(productInfo.getAsinCode());
                outboundInfo.setOutboundGoodsID(productInfo.getId().toString());
                outboundInfo.setProductName(productInfo.getProEghName());
                outboundInfo.setTheImagePath(productInfo.getProPicture());
            }
            list.add(outboundInfo);
        }
        orderInfo.setOutboundInfos(list);
        return orderInfo;
    }

    /**
     * 判断是否已经存在该订单
     * @param order
     * @param oldOrders
     * @return
     */
    @Override
    public Boolean orderInfoExist(Order order, List<OrderInfo> oldOrders){
        QueryWrapper<OrderInfo> wrapper=new QueryWrapper<>();
        wrapper.eq("OrderID",order.getOut_order_no());
        OrderInfo orderinfo=orderInfoService.getOne(wrapper);
        if (null!=orderinfo){
            log.info("已在系统: "+orderinfo.getOrderID());
            if (orderinfo.getAmazonUpdateDate()==null){
                return true;
            }
            orderinfo.setAmazonUpdateDate(DateUtils.getTimeNow());
            orderinfo.setId(null);
            oldOrders.add(orderinfo);
            return true;
        }
        return false;
    }

    /**
     * 判断是否已存在于pendingOrder,存在就更新
     * @param order
     * @param shopId
     * @return
     */
    @Override
    @Transactional(value="transactionManager",rollbackFor = Exception.class)
    public Boolean pendingExist(Order order, Integer shopId) throws Exception{
        QueryWrapper<PendingOrder> wrapper=new QueryWrapper<>();
        wrapper.eq("OrderID",order.getOut_order_no());
        wrapper.eq("shopID",shopId);
        PendingOrder pendingOrder=pendingOrderService.getOne(wrapper);
        if (pendingOrder!=null){
            pendingOrder.setAddress(order.getAddress().getDetail());
            pendingOrder.setAddressee(order.getAddress().getConsignee());
            QueryWrapper<RegionInfo> wrapper1=new QueryWrapper<>();
            wrapper1.eq("RegionAbbr",order.getAddress().getCountry());
            wrapper1.eq("ParentID",0);
            pendingOrder.setCity(order.getAddress().getCity());
            pendingOrder.setZipCode(order.getAddress().getPostcode());
            pendingOrder.setCountry(regionInfoService.getOne(wrapper1).getRegionEghName());
            pendingOrder.setProvince(order.getAddress().getProvince());
            pendingOrder.setPhone(order.getAddress().getMobile_phone());
            pendingOrder.setAmazonUpdateDate(DateUtils.getTimeNow());
            pendingOrder.setId(null);
            Boolean i= false;
            try {
                i=pendingOrderService.update(pendingOrder,wrapper);
            }catch (Exception e){
                e.printStackTrace();
                throw new Exception();
            }
            if (i){
                log.info("更新Pending: "+pendingOrder.getOrderID());
            }
            return true;
        }
        return false;
    }

    @Override
    @Transactional(value="transactionManager",rollbackFor = Exception.class)
    public Boolean isPending(OrderInfo orderInfo, ShopConfig shopConfig) throws Exception {
        Boolean flag=false;
        for (OutboundInfo outboundInfo:orderInfo.getOutboundInfos()) {
            if (productInfoService.getProductInfo(outboundInfo.getASINYard()).isEmpty()){
                flag=true;
            }
        }
        if (flag){
            PendingOrder pendingOrder=new PendingOrder();
            pendingOrder.setUserID(shopConfig.getUserID());
            pendingOrder.setAmazonStatus("");
            pendingOrder.setSystemNumber(orderInfo.getSystemNumber());
            pendingOrder.setOrderID(orderInfo.getOrderID());
            pendingOrder.setSaleDate(orderInfo.getSaleDate());
            pendingOrder.setStopDate(orderInfo.getStopDate());
            pendingOrder.setAddressee(orderInfo.getAddressee());
            pendingOrder.setAddress(orderInfo.getAddress());
            pendingOrder.setCity(orderInfo.getCity());
            pendingOrder.setCountry(orderInfo.getCountry());
            pendingOrder.setProvince(orderInfo.getProvince());
            pendingOrder.setZipCode(orderInfo.getZipCode());
            pendingOrder.setPhone(orderInfo.getPhone());
            pendingOrder.setEmail(orderInfo.getEmail());
            pendingOrder.setOrderState(0);
            pendingOrder.setTotalPrice(orderInfo.getTotalPrice());
            pendingOrder.setOrderRemarks(orderInfo.getOrderRemarks());
            pendingOrder.setShopID(orderInfo.getShopID());
            pendingOrder.setShopKey(shopConfig.getShopKey());
            pendingOrder.setAmazonUpdateDate(orderInfo.getAmazonUpdateDate());
            QueryWrapper<RegionInfo> wrapper=new QueryWrapper<>();
            wrapper.eq("RegionEghName",orderInfo.getCountry());
            pendingOrder.setCurrencyCode(regionInfoService.getOne(wrapper).getCurrencyCode());
            pendingOrder.setPurchaseDate(DateUtils.getTimeNow());
            pendingOrder.setPlatform(orderInfo.getPlatform());
            Boolean i= pendingOrderService.save(pendingOrder);
            if (i){
                Boolean j=true;
                for (OutboundInfo outboundInfo:orderInfo.getOutboundInfos()) {
                    PendingOutBound pendingOutBound=new PendingOutBound();
                    pendingOutBound.setOrderItemID("0");
                    pendingOutBound.setProductName(outboundInfo.getProductName());
                    pendingOutBound.setOutboundGoodsID("0");
                    pendingOutBound.setOutboundNumder(outboundInfo.getOutboundNumder());
                    pendingOutBound.setProductAddress(outboundInfo.getProductAddress());
                    pendingOutBound.setOutboundNumder(outboundInfo.getOutboundNumder());
                    pendingOutBound.setPrice(outboundInfo.getPrice());
                    pendingOutBound.setThePricePaid(outboundInfo.getThePricePaid());
                    pendingOutBound.setTheImagePath(outboundInfo.getTheImagePath());
                    pendingOutBound.setOutboundTime(LocalDateTime.now());
                    pendingOutBound.setSystemNumber(outboundInfo.getSystemNumber());
                    pendingOutBound.setRemarks(outboundInfo.getRemarks());
                    pendingOutBound.setOutState(1);
                    pendingOutBound.setASINYard(outboundInfo.getASINYard());
                    pendingOutBound.setAmazonPrice(outboundInfo.getAmazonPrice());
                    pendingOutBound.setProductClass(outboundInfo.getProductClass());
                    pendingOutBound.setOutboundGoodsID(outboundInfo.getOutboundGoodsID());
                    pendingOutBound.setGetState(1);
                    String imgurl=HttpUtil.sendGet(IMG_UPLOAD_URL+outboundInfo.getTheImagePath());
                    if (!"".equals(imgurl)){
                        pendingOutBound.setTheImagePath(imgurl);
                    }

                    try {
                        j=j&pendingOutBoundService.save(pendingOutBound);
                    }catch (Exception e){
                        e.printStackTrace();
                        throw new Exception();
                    }
                }
                if (i&j){
                    memoService.save(orderInfo.getMemo());
                    orderInfoService.makeLog(orderInfo.getOrderID(),"自动录入订单");
                    log.info("加入Pending: "+pendingOrder.getOrderID());
                }
            }
        }

        return flag;
    }

    @Override
    @Transactional(value="transactionManager",rollbackFor = Exception.class)
    public void makeLog(String orderId,String log){
        OrderRecord orderRecord=new OrderRecord();
        orderRecord.setIp("系统自动");
        orderRecord.setCreateDate(DateUtils.getTimeNow());
        orderRecord.setStatus(0);
        orderRecord.setUserID(1);
        orderRecord.setOrderID(orderId);
        orderRecord.setOperateContent(log);
        orderRecordService.save(orderRecord);
    }

}
