package com.zgkj.api.express.ups.entity.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Map;

/**
 * @Author: Lucent
 * @Date: 2020/1/6 15:37
 */
@Data
public class PackageResults {
    @JSONField(name = "TrackingNumber")
    private String TrackingNumber;
    @JSONField(name = "ShippingLabel")
    private ShippingLabel ShippingLabel;
}
