package com.zgkj.api.trader.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单备注表
 * </p>
 *
 * @author Lucent
 * @since 2019-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("OrderInfoMemo")
public class OrderInfoMemo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("orderId")
    private String orderId;

    @TableField("orderMemo")
    private String orderMemo;

    @TableField("updateDate")
    private String updateDate;


}
