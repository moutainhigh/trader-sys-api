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
 * 
 * </p>
 *
 * @author Lucent
 * @since 2019-12-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ShopConfig")
public class ShopConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @TableField("UserID")
    private Integer UserID;

    @TableField("ShopID")
    private Integer ShopID;

    @TableField("ShopKey")
    private String ShopKey;

    @TableField("Status")
    private Integer Status;

    @TableField("CerateDate")
    private String CerateDate;

    private String account;

    private Integer platform;

    @TableField("isExpress")
    private Integer isExpress;

    @TableField("getOrderTime")
    private String getOrderTime;

    @TableField("companyId")
    private Integer companyId;

    @TableField("expTimeNum")
    private Integer expTimeNum;

    @TableField("autoPurchase")
    private Integer autoPurchase;

    @TableField("amzonStatus")
    private String amzonStatus;

    private Integer manualRecord;


}
