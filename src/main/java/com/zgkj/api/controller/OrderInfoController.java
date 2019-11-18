package com.zgkj.api.controller;

import com.util.ExcelUtils;
import com.zgkj.api.service.IOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RequestMapping("/orderinfo")
@RestController
public class OrderInfoController {

    @Autowired
    private IOrderInfoService orderInfoService;

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

}
