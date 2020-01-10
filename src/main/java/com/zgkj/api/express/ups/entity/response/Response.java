package com.zgkj.api.express.ups.entity.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Author: Lucent
 * @Date: 2020/1/6 15:25
 */
@Data
public class Response {
    @JSONField(name = "TransactionReference")
    private String TransactionReference;
    @JSONField(name = "ResponseStatus")
    private ResponseStatus ResponseStatus;
    @JSONField(name = "Alert")
    private Alert Alert;
}
