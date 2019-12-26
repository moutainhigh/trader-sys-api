package com.zgkj.api.tradeFlow.controller;


import com.zgkj.util.ExcelUtils;
import com.zgkj.api.tradeFlow.service.IOrderinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Lucent
 * @since 2019-11-19
 */
@RestController
@RequestMapping("/tradeFlow/orderinfo")
public class OrderinfoController {

    @Autowired
    IOrderinfoService orderinfoService;


    @ResponseBody
    @RequestMapping("exportOperatorData")
    public String exportOperatorData(String start,String end,HttpServletResponse response){
        try {
            List<List<Map<String,Object>>> data=new ArrayList<>();
            data.add(orderinfoService.cntOperatorMapData(start, end));
            data.add(orderinfoService.cntOperatorMapData1(start, end));
            data.add(orderinfoService.cntOperatorMapData2(start, end));
            String[] sheets={"详细","未重新下单","重新下单"};
            ExcelUtils.exportExcel(data,null,sheets,"美术延迟率"+start+"至"+end+".xlsx",response);
            return "";
        }catch (Exception e){
            e.printStackTrace();
            return "导出失败"+e.toString();
        }
    }
}
