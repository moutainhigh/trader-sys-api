package com.zgkj.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zgkj.api.trader.entity.ShopConfig;
import com.zgkj.api.trader.service.IShopConfigService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class test {
    @Autowired
    IShopConfigService shopConfigService;

    @Test
    public void main() {
        QueryWrapper<ShopConfig> wrapper=new QueryWrapper<>();
        wrapper.eq("platform",4);
        List<ShopConfig> list=shopConfigService.list(wrapper);
        System.out.println(list);

        String url="http://merchantapi.sdsdiy.com/gateway/order_import_record_orders/order_previews?seller_id=cn1521362084khty&gmt_created_begin=2019-12-10%2000:00:00&gmt_created_end=2019-12-11%2023:59:59&_timestamp=1576482242905&_appid=mLHp6EmSTyQ7nIlEQDbelJAObRc8k8Iw&_sign=a30c0c41d1c49c7b2b075a9f5f679836";
        try {
            String re= HttpUtil.sendGet(url);
            System.out.println(re);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
