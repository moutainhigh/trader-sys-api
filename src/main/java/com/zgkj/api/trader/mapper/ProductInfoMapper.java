package com.zgkj.api.trader.mapper;

import com.zgkj.api.trader.entity.ProductInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Lucent
 * @since 2019-12-18
 */
public interface ProductInfoMapper extends BaseMapper<ProductInfo> {

    List<ProductInfo> getProductInfo(String asin);

}
