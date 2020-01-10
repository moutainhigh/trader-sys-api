package com.zgkj.api.express.ups.entity.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @Author: Lucent
 * @Date: 2020/1/6 15:26
 */
@Data
public class Alert {
    @JSONField(name = "Code")
    private String Code;
    @JSONField(name = "Description")
    private String Description;
}
