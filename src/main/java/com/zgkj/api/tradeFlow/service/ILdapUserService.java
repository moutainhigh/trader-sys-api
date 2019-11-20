package com.zgkj.api.tradeFlow.service;

import com.zgkj.api.tradeFlow.entity.LdapUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zgkj.api.tradeFlow.entity.LdapUserAndUserGroupBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lucent
 * @since 2019-11-19
 */
public interface ILdapUserService extends IService<LdapUser> {
    LdapUser checkOperator(String orderid);
    List<LdapUserAndUserGroupBean> getUserList(String userName);
}
