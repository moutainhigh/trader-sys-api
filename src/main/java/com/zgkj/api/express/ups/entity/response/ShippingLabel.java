package com.zgkj.api.express.ups.entity.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Map;

/**
 * @Author: Lucent
 * @Date: 2020/1/6 15:39
 */
@Data
public class ShippingLabel {
    @JSONField(name = "GraphicImage")
    private String GraphicImage;
    @JSONField(name = "HTMLImage")
    private String HTMLImage;
}
