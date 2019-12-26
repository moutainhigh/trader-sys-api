package com.zgkj.api.SchedulingTask;

import com.zgkj.api.trader.service.IOrderInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 速卖通自动录单定时任务
 * @author user
 */
@Component
@EnableScheduling
@EnableAsync
@Slf4j
public class AliExpressAutoImport {

    @Autowired
    IOrderInfoService orderInfoService;

    @Async
    @Scheduled(cron = "0 0 * * * ?")
    public void importAliExpress(){
        orderInfoService.autoImportAliExpressOrders();
    }

}
