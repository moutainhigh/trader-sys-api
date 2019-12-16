package com.zgkj.api.trader.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.util.DateUtils;
import com.util.ExcelUtils;
import com.util.IPUtil;
import com.zgkj.api.trader.entity.OrderExpressInfo;
import com.zgkj.api.trader.entity.OrderRecord;
import com.zgkj.api.trader.service.IOrderExpressInfoService;
import com.zgkj.api.trader.service.IOrderInfoService;
import com.zgkj.api.trader.service.IOrderRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping("/orderinfo")
@RestController
public class OrderInfoController {

    @Autowired
    private IOrderInfoService orderInfoService;
    @Autowired
    private IOrderExpressInfoService orderExpressInfoService;
    @Autowired
    private IOrderRecordService orderRecordService;

    @ResponseBody
    @RequestMapping("exportExcel")
    public String exportExcel(String start,String end,String groupId,HttpServletResponse response){
        try {
            List<List<Map<String,Object>>> data=new ArrayList<>();
            data.add(orderInfoService.getListedMapData(start,end,groupId));
            ExcelUtils.exportExcel(data,null,null,"每丽匙"+start+"-"+end+".xlsx",response);
            return "";
        }catch (Exception e){
            return "导出失败"+e.toString();
        }
    }

    @ResponseBody
    @RequestMapping("updateExpress")
    public List<String> updateExpress(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        String[] keys={"orderid","expNo"};
        List<Map<String,Object>> list=ExcelUtils.importExcel(file,keys);
        List<String> resList=new ArrayList<>();
        for (int i=0;i<list.size();i++){
            String result="";
            QueryWrapper<OrderExpressInfo> wrapper=new QueryWrapper<>();
            wrapper.eq("OrderID",String.valueOf(list.get(i).get("orderid")));
            OrderExpressInfo expressInfo=new OrderExpressInfo();
            expressInfo.setTheExpressNo(list.get(i).get("expNo").toString());
            expressInfo.setExpressage(20);
            expressInfo.setTypeOfShipping("USPS");
            expressInfo.setCreateDate(DateUtils.getTimeNow());
            try {
                OrderExpressInfo orderExpressInfo=orderExpressInfoService.getOne(wrapper);
                Boolean res=false;
                if (null!=orderExpressInfo&&!"".equals(orderExpressInfo)){
                    res=orderExpressInfoService.update(expressInfo,wrapper);
                    if (res){
                        result+="订单: "+list.get(i).get("orderid")+" 更新运单号: "+expressInfo.getTheExpressNo()+" 成功!";
                        addRecord(String.valueOf(list.get(i).get("orderid")),"将运单号修改为: "+expressInfo.getTheExpressNo(),request);
                    }else {
                        result+="订单: "+list.get(i).get("orderid")+" 更新运单号: "+expressInfo.getTheExpressNo()+" 失败!";
                    }
                }else if (null==orderExpressInfo||"".equals(orderExpressInfo)){
                    expressInfo.setOrderID(list.get(i).get("orderid").toString());
                    res=orderExpressInfoService.save(expressInfo);
                    if (res){
                        result+="订单: "+list.get(i).get("orderid")+" +运单号: "+expressInfo.getTheExpressNo()+" 插入成功!";
                        addRecord(String.valueOf(list.get(i).get("orderid")),"将运单号修改为: "+expressInfo.getTheExpressNo(),request);
                    }else {
                        result+="订单: "+list.get(i).get("orderid")+" +运单号: "+expressInfo.getTheExpressNo()+" 插入失败!";
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
                result+="订单: "+list.get(i).get("orderid")+" +运单号: "+expressInfo.getTheExpressNo()+" 出现异常!";
                resList.add(result);
                continue;
            }
            resList.add(result);
        }
        return resList;
    }

    private void addRecord(String orderid,String content,HttpServletRequest request){
        OrderRecord record=new OrderRecord();
        record.setCreateDate(DateUtils.getTimeNow());
        record.setIp(IPUtil.getClientIP(request));
        record.setStatus(0);
        record.setOperateContent(content);
        record.setUserID(237);
        record.setOrderID(orderid);
        orderRecordService.save(record);
    }

    @ResponseBody
    @RequestMapping("scanToUpdateExp")
    public String scanToUpdateExp(String outId,String expNo,HttpServletRequest request){
        String result="";
        expNo=expNo.substring(8);//去掉前八位
        outId=outId.replace("B","");
        String orderid=orderInfoService.getOrderIdWithOutboundId(outId);
        OrderExpressInfo expressInfo=new OrderExpressInfo();
        expressInfo.setTheExpressNo(expNo);
        expressInfo.setExpressage(20);
        expressInfo.setTypeOfShipping("USPS");
        expressInfo.setCreateDate(DateUtils.getTimeNow());
        if (null!=orderid){
            QueryWrapper<OrderExpressInfo> wrapper=new QueryWrapper<>();
            wrapper.eq("OrderID",orderid);
            OrderExpressInfo orderExpressInfo=orderExpressInfoService.getOne(wrapper);
            Boolean res=false;
            if (null!=orderExpressInfo){
                res=orderExpressInfoService.update(expressInfo,wrapper);
                if (res){
                    result+="订单: "+orderid+" 更新运单号: "+expNo+" 成功!";
                    addRecord(orderid,"将运单号修改为: "+expNo,request);
                }else {
                    result+="订单: "+orderid+" 更新运单号: "+expNo+" 失败!";
                }
            }else {
                expressInfo.setOrderID(orderid);
                res=orderExpressInfoService.save(expressInfo);
                if (res){
                    result+="订单: "+orderid+" 运单号: "+expNo+" 插入成功!";
                    addRecord(orderid,"将运单号修改为: "+expNo,request);
                }else {
                    result+="订单: "+orderid+" 运单号: "+expNo+" 插入失败!";
                }
            }
        }else {
            return "id "+outId+" 查询订单编号失败！";
        }
        return result;
    }
}
