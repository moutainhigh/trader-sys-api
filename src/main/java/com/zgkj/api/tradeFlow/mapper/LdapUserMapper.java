package com.zgkj.api.tradeFlow.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.zgkj.api.tradeFlow.entity.LdapUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zgkj.api.tradeFlow.entity.LdapUserAndUserGroupBean;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Lucent
 * @since 2019-11-19
 */
@DS("tradeFlow")
public interface LdapUserMapper extends BaseMapper<LdapUser> {
    LdapUser checkOperator(String orderid);
    List<LdapUserAndUserGroupBean> getUserList(String userName);
}
