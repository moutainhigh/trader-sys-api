package com.zgkj.api.express.ups.entity.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Author: Lucent
 * @Date: 2020/1/6 15:03
 */
@Data
public class PackageWeight {
    @JSONField(name = "UnitOfMeasurement")
    private UnitOfMeasurement UnitOfMeasurement;
    @JSONField(name = "Weight")
    private String Weight;
}
