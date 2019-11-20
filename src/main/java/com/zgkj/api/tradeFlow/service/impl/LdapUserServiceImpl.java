package com.zgkj.api.tradeFlow.service.impl;

import com.zgkj.api.tradeFlow.entity.LdapUser;
import com.zgkj.api.tradeFlow.entity.LdapUserAndUserGroupBean;
import com.zgkj.api.tradeFlow.mapper.LdapUserMapper;
import com.zgkj.api.tradeFlow.service.ILdapUserService;
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
 * @since 2019-11-19
 */
@Service
public class LdapUserServiceImpl extends ServiceImpl<LdapUserMapper, LdapUser> implements ILdapUserService {
    @Autowired
    LdapUserMapper ldapUserMapper;

    public LdapUser checkOperator(String orderid){
        return ldapUserMapper.checkOperator(orderid);
    }

    public List<LdapUserAndUserGroupBean> getUserList(String userName){
        return ldapUserMapper.getUserList(userName);
    }
}
