package com.zgkj.api.express.ups.entity.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Author: Lucent
 * @Date: 2020/1/6 14:44
 */
@Data
public class Phone {
    @JSONField(name = "Number")
    private String Number;
}
