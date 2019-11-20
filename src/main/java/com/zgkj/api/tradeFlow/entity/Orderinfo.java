package com.zgkj.api.tradeFlow.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Lucent
 * @since 2019-11-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Orderinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单ID
     */
    @TableField("orderId")
    private String orderId;

    /**
     * 出库关联ID
     */
    @TableField("systemNumber")
    private String systemNumber;

    /**
     * 店铺ID
     */
    @TableField("shopId")
    private Long shopId;

    /**
     * 出单账号
     */
    @TableField("userId")
    private Long userId;

    /**
     * 发送的用户ID
     */
    @TableField("sentUserId")
    private Long sentUserId;

    /**
     * 出单日期
     */
    @TableField("saleDate")
    private LocalDateTime saleDate;

    /**
     * 总价
     */
    @TableField("totalPrice")
    private Double totalPrice;

    private Integer state;

    @TableField("orderMemo")
    private String orderMemo;

    @TableField("createDate")
    private String createDate;

    /**
     * 是否加急
     */
    @TableField("urgentState")
    private Integer urgentState;

    /**
     * 图片状态
     */
    @TableField("bigPicState")
    private Integer bigPicState;

    /**
     * 是否删除
     */
    private Integer isdel;

    /**
     * 所属平台
     */
    private Integer platform;


}
