package com.zgkj.api.express.ups.entity.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Author: Lucent
 * @Date: 2020/1/6 14:56
 */
@Data
public class BillShipper {
    @JSONField(name = "AccountNumber")
    private String AccountNumber;
}
