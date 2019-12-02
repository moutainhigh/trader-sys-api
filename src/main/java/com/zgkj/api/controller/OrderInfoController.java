package com.zgkj.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.util.DateUtils;
import com.util.ExcelUtils;
import com.util.IPUtil;
import com.zgkj.api.entity.OrderExpressInfo;
import com.zgkj.api.entity.OrderRecord;
import com.zgkj.api.service.IOrderExpressInfoService;
import com.zgkj.api.service.IOrderInfoService;
import com.zgkj.api.service.IOrderRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
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
            ExcelUtils.exportExcel(orderInfoService.getListedMapData(start,end,groupId),null,"每丽匙"+start+"-"+end+".xlsx",response);
            return "";
        }catch (Exception e){
            return "导出失败"+e.toString();
        }
    }

    @ResponseBody
    @RequestMapping("updateExpress")
    public List<String> updateExpress(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        DecimalFormat df = new DecimalFormat("0");
        String[] keys={"orderid","expNo"};
        List<Map<String,Object>> list=ExcelUtils.importExcel(file,keys);
        List<String> resList=new ArrayList<>();
        for (int i=0;i<list.size();i++){
            String result="";
            QueryWrapper<OrderExpressInfo> wrapper=new QueryWrapper<>();
            wrapper.eq("OrderID",String.valueOf(list.get(i).get("orderid")));
            OrderExpressInfo expressInfo=new OrderExpressInfo();
            expressInfo.setTheExpressNo(df.format(Double.valueOf(list.get(i).get("expNo").toString())));
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
                        addRecord(String.valueOf(list.get(i).get("orderid")),expressInfo.getTheExpressNo(),request);
                    }else {
                        result+="订单: "+list.get(i).get("orderid")+" 更新运单号: "+expressInfo.getTheExpressNo()+" 失败!";
                    }
                }else if (null==orderExpressInfo||"".equals(orderExpressInfo)){
                    res=orderExpressInfoService.save(expressInfo);
                    if (res){
                        result+="订单: "+list.get(i).get("orderid")+" +运单号: "+expressInfo.getTheExpressNo()+" 插入成功!";
                        addRecord(String.valueOf(list.get(i).get("orderid")),expressInfo.getTheExpressNo(),request);
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

    private void addRecord(String orderid,String expNo,HttpServletRequest request){
        OrderRecord record=new OrderRecord();
        record.setCreateDate(DateUtils.getTimeNow());
        record.setIp(IPUtil.getClientIP(request));
        record.setStatus(0);
        record.setOperateContent("将运单号修改为: "+expNo);
        record.setUserID(237);
        record.setOrderID(orderid);
        orderRecordService.save(record);
    }

}
