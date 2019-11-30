package com.zgkj.api.entity;

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
 * @since 2019-11-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("orderExpressInfo")
public class OrderExpressInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("OrderID")
    private String OrderID;

    @TableField("TheExpressNo")
    private String TheExpressNo;

    private Integer expressage;

    @TableField("typeOfShipping")
    private String typeOfShipping;

    @TableField("createDate")
    private String createDate;

    @TableField("expressNoSer")
    private String expressNoSer;

    @TableField("OrderFreight")
    private String OrderFreight;

    @TableField("OrderWeight")
    private String OrderWeight;

    @TableField("oldExpressNo")
    private String oldExpressNo;

    @TableField("cargoTrackinginfo")
    private String cargoTrackinginfo;

    @TableField("Remake")
    private String Remake;

    @TableField("sysWeight")
    private String sysWeight;


}
