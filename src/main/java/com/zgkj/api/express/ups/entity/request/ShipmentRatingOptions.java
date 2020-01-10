package com.zgkj.api.express.ups.entity.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Author: Lucent
 * @Date: 2020/1/6 15:07
 */
@Data
public class ShipmentRatingOptions {
    @JSONField(name = "NegotiatedRatesIndicator")
    private String NegotiatedRatesIndicator;
}
