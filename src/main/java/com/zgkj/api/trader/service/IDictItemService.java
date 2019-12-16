package com.zgkj.api.trader.service;

import com.zgkj.api.trader.entity.DictItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lucent
 * @since 2019-11-15
 */
public interface IDictItemService extends IService<DictItem> {
    List<DictItem> getListbyType();
}
