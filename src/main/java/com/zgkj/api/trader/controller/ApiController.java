package com.zgkj.api.trader.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.util.CipherUtil;
import com.zgkj.api.Auth.exception.ServerErrorException;
import com.zgkj.api.trader.entity.UserInfo;
import com.zgkj.api.trader.service.IOrderInfoService;
import com.zgkj.api.trader.service.IOutboundInfoService;
import com.zgkj.api.trader.service.IUserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RequestMapping("/api")
@RestController
public class ApiController {
    private static final Logger log = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    private IUserInfoService userInfoService;
    @Autowired
    private IOrderInfoService orderInfoService;
    @Autowired
    private IOutboundInfoService outboundInfoService;

    @PostMapping("/create")
    public String create() throws Exception {
       return "hello";
    }

    @GetMapping("/uid")
    public Integer getUid(String UserName,String PassWord){
        QueryWrapper<UserInfo> wrapper=new QueryWrapper<>();
        wrapper.eq("UserName",UserName);
        wrapper.eq("PassWord", CipherUtil.DESEncrypt(PassWord));
        UserInfo userInfo=userInfoService.getOne(wrapper);
        if (null!=userInfo){
            return userInfo.getId();
        }
        throw new ServerErrorException("500","账号或密码错误");
    }

    @PostMapping("getOutboundGoodsId")
    public List<Map<String,String>> getOutboundGoodsId(@RequestBody JSONObject jsonObject){
        Integer uid=Integer.valueOf(jsonObject.get("Uid").toString());
        String start=jsonObject.get("StartDate").toString();
        String end=jsonObject.get("StopDate").toString();
        return outboundInfoService.getAllId(uid,start,end);
    }
}
