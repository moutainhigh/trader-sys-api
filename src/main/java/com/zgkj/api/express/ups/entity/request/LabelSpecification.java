package com.zgkj.api.express.ups.entity.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Author: Lucent
 * @Date: 2020/1/6 15:09
 */
@Data
public class LabelSpecification {
    @JSONField(name = "HTTPUserAgent")
    private String HTTPUserAgent;
    @JSONField(name = "LabelPrintMethod")
    private LabelPrintMethod LabelPrintMethod;
    @JSONField(name = "LabelImageFormat")
    private LabelImageFormat LabelImageFormat;
}
