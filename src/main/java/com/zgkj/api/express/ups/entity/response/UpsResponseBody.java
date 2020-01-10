package com.zgkj.api.express.ups.entity.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Author: Lucent
 * @Date: 2020/1/6 15:44
 */
@Data
public class UpsResponseBody {
    @JSONField(name = "ShipmentResponse")
    private ShipmentResponse ShipmentResponse;
}
