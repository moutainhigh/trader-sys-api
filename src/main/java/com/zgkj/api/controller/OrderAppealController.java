package com.zgkj.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zgkj.api.Auth.exception.ServerErrorException;
import com.zgkj.api.entity.OrderAppeal;
import com.zgkj.api.entity.ServiceFrom;
import com.zgkj.api.service.IOrderAppealService;
import com.zgkj.api.service.IServiceFromService;
import com.zgkj.api.tradeFlow.service.ILdapUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Lucent
 * @since 2019-11-20
 */
@RestController
@RequestMapping("order-appeal")
public class OrderAppealController {
    @Autowired
    IOrderAppealService orderAppealService;
    @Autowired
    ILdapUserService ldapUserService;
    @Autowired
    IServiceFromService serviceFromService;


    @ResponseBody
    @RequestMapping("save")
    public String save(OrderAppeal orderAppeal){
        String operator=ldapUserService.checkOperator(orderAppeal.getOrderid()).getUsernameCn();
        if (null!=operator&&!"".equals(operator)){
            orderAppeal.setOperator(operator);
        }else {
            orderAppeal.setOperator("暂无数据");
        }
        try {
            Boolean i=orderAppealService.save(orderAppeal);
            if (i){
                QueryWrapper<ServiceFrom> wrapper=new QueryWrapper<>();
                wrapper.eq("OrderID",orderAppeal.getOrderid());
                ServiceFrom serviceFrom=new ServiceFrom();
                serviceFrom.setAppealstatus(1);
                serviceFromService.update(serviceFrom,wrapper);
                return "提交成功！";
            }else {
                return "提交失败!";
            }
        }catch (DuplicateKeyException e){
            throw new ServerErrorException("500","该订单已在申诉中!");
        }
    }

    @ResponseBody
    @RequestMapping("del")
    public String del(String orderid){
        Boolean i= false;
        QueryWrapper<OrderAppeal> wrapper=new QueryWrapper<>();
        wrapper.eq("orderid",orderid);
        i=orderAppealService.remove(wrapper);
        if (i){
            QueryWrapper<ServiceFrom> wrapper1=new QueryWrapper<>();
            wrapper1.eq("OrderID",orderid);
            ServiceFrom serviceFrom=new ServiceFrom();
            serviceFrom.setAppealstatus(0);
            serviceFromService.update(serviceFrom,wrapper1);
            return "撤销成功";
        }else {
            return "撤销失败";
        }
    }

    @ResponseBody
    @RequestMapping("check")
    public OrderAppeal check(String orderid){
        QueryWrapper<OrderAppeal> wrapper=new QueryWrapper<>();
        wrapper.eq("orderid",orderid);
        return orderAppealService.getOne(wrapper);
    }

    @ResponseBody
    @RequestMapping("reject")
    public String reject(String orderid){
        QueryWrapper<ServiceFrom> wrapper1=new QueryWrapper<>();
        wrapper1.eq("OrderID",orderid);
        ServiceFrom serviceFrom=new ServiceFrom();
        serviceFrom.setAppealstatus(2);
        Boolean i= serviceFromService.update(serviceFrom,wrapper1);
        if (i){
            return "success";
        }else {
            return "failed";
        }
    }

}
