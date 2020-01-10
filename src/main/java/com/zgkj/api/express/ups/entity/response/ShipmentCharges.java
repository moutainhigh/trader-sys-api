package com.zgkj.api.express.ups.entity.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Map;

/**
 * @Author: Lucent
 * @Date: 2020/1/6 15:29
 */
@Data
public class ShipmentCharges {
    /**
     * keys: CurrencyCode,MonetaryValue
     */
    @JSONField(name = "TransportationCharges")
    private Map<String,Object> TransportationCharges;
    @JSONField(name = "ServiceOptionsCharges")
    private Map<String,Object> ServiceOptionsCharges;
    @JSONField(name = "TotalCharges")
    private Map<String,Object> TotalCharges;
}
