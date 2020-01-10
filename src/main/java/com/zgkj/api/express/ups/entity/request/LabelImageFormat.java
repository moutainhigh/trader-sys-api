package com.zgkj.api.express.ups.entity.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Author: Lucent
 * @Date: 2020/1/6 15:11
 */
@Data
public class LabelImageFormat {
    @JSONField(name = "Code")
    private String Code;
}
