package com.zgkj.api.express.ups.entity.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Author: Lucent
 * @Date: 2020/1/6 14:41
 */
@Data
public class Request {
    @JSONField(name = "RequestOption")
    private String RequestOption;
    @JSONField(name = "SubVersion")
    private String SubVersion;
}
