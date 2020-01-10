package com.zgkj.api.express.ups.entity.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Map;

/**
 * @Author: Lucent
 * @Date: 2020/1/6 15:32
 */
@Data
public class BillingWeight {
    @JSONField(name = "Weight")
    private String Weight;
    /**
     * keys: Code,Description
     */
    @JSONField(name = "UnitOfMeasurement")
    private Map<String,Object> UnitOfMeasurement;
}
