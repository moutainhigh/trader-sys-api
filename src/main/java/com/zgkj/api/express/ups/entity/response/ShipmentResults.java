package com.zgkj.api.express.ups.entity.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Author: Lucent
 * @Date: 2020/1/6 15:28
 */
@Data
public class ShipmentResults {
    @JSONField(name = "ShipmentIdentificationNumber")
    private String ShipmentIdentificationNumber;
    @JSONField(name = "PackageResults")
    private PackageResults PackageResults;
}
