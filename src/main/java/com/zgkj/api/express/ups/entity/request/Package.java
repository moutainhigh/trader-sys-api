package com.zgkj.api.express.ups.entity.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Author: Lucent
 * @Date: 2020/1/6 15:01
 */
@Data
public class Package {
    @JSONField(name = "Description")
    private String Description;
    @JSONField(name = "Packaging")
    private Packaging Packaging;
    @JSONField(name = "PackageWeight")
    private PackageWeight PackageWeight;
    /**
     * key必须是Value
     */
    @JSONField(name = "ReferenceNumber")
    private List<Map<String,String>> ReferenceNumber;
}
