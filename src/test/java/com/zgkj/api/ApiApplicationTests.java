package com.zgkj.api;


import com.zgkj.api.trader.service.IOrderInfoService;
import com.zgkj.api.trader.service.IProductInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiApplicationTests {

    @Autowired
    IOrderInfoService orderInfoService;
    @Autowired
    IProductInfoService productInfoService;

    @Test
    public void contextLoads() {
        try {
            orderInfoService.autoImportAliExpressOrders();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
