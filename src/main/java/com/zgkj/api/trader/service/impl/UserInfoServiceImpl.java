package com.zgkj.api.trader.service.impl;

import com.zgkj.api.trader.entity.UserInfo;
import com.zgkj.api.trader.mapper.UserInfoMapper;
import com.zgkj.api.trader.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lucent
 * @since 2019-11-06
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
