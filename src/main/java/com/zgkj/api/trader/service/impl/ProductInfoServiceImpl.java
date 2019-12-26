package com.zgkj.api.trader.service.impl;

import com.zgkj.api.trader.entity.ProductInfo;
import com.zgkj.api.trader.mapper.ProductInfoMapper;
import com.zgkj.api.trader.service.IProductInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lucent
 * @since 2019-12-18
 */
@Service
public class ProductInfoServiceImpl extends ServiceImpl<ProductInfoMapper, ProductInfo> implements IProductInfoService {

    @Autowired
    ProductInfoMapper productInfoMapper;

    @Override
    public List<ProductInfo> getProductInfo(String asin){
        return productInfoMapper.getProductInfo(asin);
    }
}
