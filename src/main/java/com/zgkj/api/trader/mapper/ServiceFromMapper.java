package com.zgkj.api.trader.mapper;

import com.zgkj.api.trader.entity.ServiceFrom;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Lucent
 * @since 2019-11-13
 */
public interface ServiceFromMapper extends BaseMapper<ServiceFrom> {

    List<ServiceFrom> getServiceFromList(String start,String end,String orderid);
    List<ServiceFrom> getAppealList(@Param("orderid") String orderid);
}
