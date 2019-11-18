package com.zgkj.api.service;

import com.zgkj.api.entity.ServiceFrom;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lucent
 * @since 2019-11-13
 */
public interface IServiceFromService extends IService<ServiceFrom> {

    List<ServiceFrom> getServiceFromList(String start,String end);
}
