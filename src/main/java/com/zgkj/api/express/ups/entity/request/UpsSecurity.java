package com.zgkj.api.express.ups.entity.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Author: Lucent
 * @Date: 2020/1/6 14:26
 */
@Data
public class UpsSecurity {
    @JSONField(name = "UsernameToken")
    private UsernameToken UsernameToken;
    @JSONField(name = "ServiceAccessToken")
    private ServiceAccessToken ServiceAccessToken;
}
