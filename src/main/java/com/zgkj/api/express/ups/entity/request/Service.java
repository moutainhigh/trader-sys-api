package com.zgkj.api.express.ups.entity.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Author: Lucent
 * @Date: 2020/1/6 14:58
 */
@Data
public class Service {
    @JSONField(name = "Code")
    private String Code;
    @JSONField(name = "Description")
    private String Description;
}
