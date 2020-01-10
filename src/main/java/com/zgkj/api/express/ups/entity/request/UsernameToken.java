package com.zgkj.api.express.ups.entity.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Author: Lucent
 * @Date: 2020/1/6 14:33
 */
@Data
public class UsernameToken {
    @JSONField(name = "Username")
    private String Username;
    @JSONField(name = "Password")
    private String Password;
}
