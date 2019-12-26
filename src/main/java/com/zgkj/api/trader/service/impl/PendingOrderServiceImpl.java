package com.zgkj.api.trader.service.impl;

import com.zgkj.api.trader.entity.PendingOrder;
import com.zgkj.api.trader.mapper.PendingOrderMapper;
import com.zgkj.api.trader.service.IPendingOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lucent
 * @since 2019-12-18
 */
@Service
public class PendingOrderServiceImpl extends ServiceImpl<PendingOrderMapper, PendingOrder> implements IPendingOrderService {

}
