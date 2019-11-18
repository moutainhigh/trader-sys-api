package com.zgkj.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.util.DateUtil;
import com.util.DateUtils;
import com.zgkj.api.entity.DictItem;
import com.zgkj.api.entity.ProductClass;
import com.zgkj.api.entity.ServiceFrom;
import com.zgkj.api.service.IDictItemService;
import com.zgkj.api.service.IProductClassService;
import com.zgkj.api.service.IServiceFromService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Lucent
 * @since 2019-11-13
 */
@RestController
@RequestMapping("servicefrom")
public class ServiceFromController {
    @Autowired
    IServiceFromService serviceFromService;
    @Autowired
    IProductClassService productClassService;
    @Autowired
    IDictItemService dictItemService;

    @ResponseBody
    @RequestMapping("getList")
    public List<ServiceFrom> getList(String start,String end){
        if((null==end||"".equals(end))&&(null==start||"".equals(start))){
            end=DateUtils.getDateNow()+" 23:59:59";
            start=DateUtils.getDateBefore(30)+" 00:00:00";
        }else {
            end+=" 23:59:59";
            start+=" 00:00:00";
        }
        List<ServiceFrom> list=serviceFromService.getServiceFromList(start,end);
        return list;
    }

    @ResponseBody
    @RequestMapping("checkReason")
    public String checkReason(String ids){
        String[] idList=ids.split("-");
        String result="";
        QueryWrapper<ProductClass> wrapper=new QueryWrapper<>();
        wrapper.eq("ID",idList[0]);
        ProductClass productClass=productClassService.getOne(wrapper);
        result+=productClass.getClassifyName()+"→";
        wrapper=new QueryWrapper<>();
        wrapper.eq("ID",idList[1]);
        productClass=productClassService.getOne(wrapper);
        result+=productClass.getClassifyName()+"→";
        List<DictItem> dictItems=dictItemService.getListbyType();
        String show_Reason = "";
        if(idList[2].contains("000")){
            for(DictItem di : dictItems){
                if(Integer.parseInt(di.getItemValue()) == 0){
                    continue;
                }
                if(Integer.parseInt(idList[2].split("000")[0]) == Integer.parseInt(di.getItemValue())){
                    show_Reason = di.getItemCnName();
                }
            }
        }else{
            wrapper=new QueryWrapper<>();
            wrapper.eq("ID",Integer.parseInt(idList[2].split("000")[0]));
            productClass=productClassService.getOne(wrapper);
            if (null!=productClass){
                show_Reason = productClass.getClassifyName();
            }
        }
        result+=show_Reason;
        return result;
    }

}
