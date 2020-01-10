package com.zgkj.api.express.ups.entity.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Author: Lucent
 * @Date: 2020/1/6 15:13
 */
@Data
public class UpsRequestBody {
    @JSONField(name = "UPSSecurity")
    private UpsSecurity UPSSecurity;
    @JSONField(name = "ShipmentRequest")
    private ShipmentRequest ShipmentRequest;
}
