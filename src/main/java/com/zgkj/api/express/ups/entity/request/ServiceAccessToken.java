package com.zgkj.api.express.ups.entity.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Author: Lucent
 * @Date: 2020/1/6 14:33
 */
@Data
public class ServiceAccessToken {
    @JSONField(name = "AccessLicenseNumber")
    private String AccessLicenseNumber = "ED72F766E6935ED2";
}
