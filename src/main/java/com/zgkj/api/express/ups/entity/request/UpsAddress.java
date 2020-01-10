package com.zgkj.api.express.ups.entity.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Author: Lucent
 * @Date: 2020/1/6 14:48
 */
@Data
public class UpsAddress {
    @JSONField(name = "City")
    private String City;
    @JSONField(name = "StateProvinceCode")
    private String StateProvinceCode;
    @JSONField(name = "PostalCode")
    private String PostalCode;
    @JSONField(name = "CountryCode")
    private String CountryCode;
    @JSONField(name = "AddressLine")
    private String[] AddressLine;
}
