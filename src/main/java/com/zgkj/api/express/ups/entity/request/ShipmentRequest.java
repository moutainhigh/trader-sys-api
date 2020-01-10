package com.zgkj.api.express.ups.entity.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Author: Lucent
 * @Date: 2020/1/6 15:14
 */
@Data
public class ShipmentRequest {
    @JSONField(name = "Request")
    private Request Request;
    @JSONField(name = "Shipment")
    private Shipment Shipment;
    @JSONField(name = "LabelSpecification")
    private LabelSpecification LabelSpecification;
}
