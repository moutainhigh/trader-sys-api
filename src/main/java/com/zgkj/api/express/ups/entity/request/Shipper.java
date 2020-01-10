package com.zgkj.api.express.ups.entity.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Author: Lucent
 * @Date: 2020/1/6 14:44
 */
@Data
public class Shipper {
    @JSONField(name = "Name")
    private String Name;
    @JSONField(name = "AttentionName")
    private String AttentionName;
    @JSONField(name = "Phone")
    private Phone Phone;
    @JSONField(name = "ShipperNumber")
    private String ShipperNumber;
    @JSONField(name = "Address")
    private UpsAddress Address;
}
