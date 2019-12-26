package com.zgkj.api.trader.service;

import com.zgkj.api.trader.entity.ProductInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zgkj.api.trader.mapper.ProductInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lucent
 * @since 2019-12-18
 */
public interface IProductInfoService extends IService<ProductInfo> {
    List<ProductInfo> getProductInfo(String asin);

}
