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
import com.zgkj.api.tradeFlow.entity.LdapUser;
import com.zgkj.api.tradeFlow.entity.LdapUserAndUserGroupBean;
import com.zgkj.api.tradeFlow.service.ILdapUserService;
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
    @Autowired
    ILdapUserService ldapUserService;

    @ResponseBody
    @RequestMapping("getList")
    public List<ServiceFrom> getList(String start,String end,String uid,String orderid){
        if((null==end||"".equals(end))&&(null==start||"".equals(start))){
            end=DateUtils.getDateNow()+" 23:59:59";
            start=DateUtils.getDateBefore(30)+" 00:00:00";
        }else {
            end+=" 23:59:59";
            start+=" 00:00:00";
        }
        List<ServiceFrom> list=serviceFromService.getServiceFromList(start,end,orderid);
        if (null!=uid&&!"".equals(uid)){
            List<ServiceFrom> list1=new ArrayList<>();
            for (ServiceFrom serviceFrom:list) {
                LdapUser ldapUser=ldapUserService.checkOperator(serviceFrom.getOrderID());
                if (null!=ldapUser&&ldapUser.getUid().equals(uid)){
                    list1.add(serviceFrom);
                }
            }
            return list1;
        }
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

    @ResponseBody
    @RequestMapping("checkOperator")
    public LdapUser checkOperator(String orderid){
        LdapUser ldapUser=ldapUserService.checkOperator(orderid);
        if (null!=ldapUser&&!"".equals(ldapUser)){
            return ldapUser;
        }else {
            return null;
        }
    }

    @ResponseBody
    @RequestMapping("userList")
    public List<LdapUserAndUserGroupBean> userList(){
        return ldapUserService.getUserList("yujie_lin");
    }


}
