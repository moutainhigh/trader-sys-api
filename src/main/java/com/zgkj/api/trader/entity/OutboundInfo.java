package com.zgkj.api.trader.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author Lucent
 * @since 2019-11-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("OutboundInfo")
public class OutboundInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @TableField("ProductClass")
    private String ProductClass;

    @TableField("ProductName")
    private String ProductName;

    @TableField("OutboundGoodsID")
    private String OutboundGoodsID;

    @TableField("ProductAddress")
    private String ProductAddress;

    @TableField("OutboundNumder")
    private Integer OutboundNumder;

    @TableField("Weight")
    private String Weight;

    @TableField("Price")
    private String Price;

    @TableField("ThePricePaid")
    private String ThePricePaid;

    @TableField("TheImagePath")
    private String TheImagePath;

    @TableField("OutboundTime")
    private Date OutboundTime;

    @TableField("SystemNumber")
    private String SystemNumber;

    @TableField("Remarks")
    private String Remarks;

    @TableField("OutState")
    private Integer OutState;

    @TableField("OrderItemID")
    private String OrderItemID;

    @TableField("ColorID")
    private Integer ColorID;

    @TableField("StyleID")
    private Integer StyleID;

    @TableField("FactoryImage")
    private String FactoryImage;

    @TableField("ASINYard")
    private String ASINYard;

    @TableField("AutoOrder")
    private Integer AutoOrder;

    @TableField("amazonPrice")
    private String amazonPrice;

    @TableField("MarketplaceId")
    private String MarketplaceId;

    @TableField("madeState")
    private Integer madeState;

    @TableField("producePicUrl")
    private String producePicUrl;

    @TableField("customizedURL")
    private String customizedURL;


}
