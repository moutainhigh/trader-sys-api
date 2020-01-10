package com.zgkj.api.express.ups.entity.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Author: Lucent
 * @Date: 2020/1/6 15:25
 */
@Data
public class ResponseStatus {
    @JSONField(name = "Code")
    private String Code;
    @JSONField(name = "Description")
    private String Description;
}
