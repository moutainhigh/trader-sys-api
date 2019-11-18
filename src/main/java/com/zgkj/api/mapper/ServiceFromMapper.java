package com.zgkj.api.mapper;

import com.zgkj.api.entity.ServiceFrom;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

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

    List<ServiceFrom> getServiceFromList(String start,String end);
}
