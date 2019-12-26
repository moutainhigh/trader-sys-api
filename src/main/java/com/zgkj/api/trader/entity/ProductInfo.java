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
 * @since 2019-12-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ProductInfo")
public class ProductInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @TableField("UserID")
    private Integer UserID;

    @TableField("ItemID")
    private Integer ItemID;

    @TableField("ProClassID")
    private Integer ProClassID;

    @TableField("ProChnName")
    private String ProChnName;

    @TableField("ProEghName")
    private String ProEghName;

    @TableField("ProUnit")
    private String ProUnit;

    @TableField("ProDescriptions")
    private String ProDescriptions;

    @TableField("ProPicture")
    private String ProPicture;

    @TableField("ProWeight")
    private String ProWeight;

    @TableField("ProSize")
    private String ProSize;

    @TableField("ReferencePrice")
    private String ReferencePrice;

    @TableField("OrderState")
    private Integer OrderState;

    @TableField("PropertyID")
    private Integer PropertyID;

    @TableField("SupplierID")
    private Integer SupplierID;

    @TableField("ProductAddress")
    private String ProductAddress;

    @TableField("ProductNumder")
    private Integer ProductNumder;

    @TableField("SKUYard")
    private String SKUYard;

    @TableField("ASINYard")
    private String ASINYard;

    @TableField("SinceNumber")
    private String SinceNumber;

    /**
     * 0、非定制  1、定制
     */
    @TableField("CustomMade")
    private Integer CustomMade;

    @TableField("ReferenceFreight")
    private String ReferenceFreight;

    @TableField("Cost")
    private String Cost;

    @TableField("StockUp")
    private Integer StockUp;

    @TableField("addType")
    private Integer addType;

    @TableField("createDate")
    private String createDate;

    @TableField("producePicUrl")
    private String producePicUrl;

    @TableField("outboundNumber")
    private Integer outboundNumber;

    @TableField("salesVolume")
    private Integer salesVolume;

    private Integer level;

    private Long matrixId;

    private Long produceCycle;

    private Integer hasProcess;

    private Float purchasingCost;

    private Integer accountedTime;

    @TableField("P_length")
    private Float pLength;

    @TableField("P_width")
    private Float pWidth;

    @TableField(exist = false)
    private String ClassifyName;
    @TableField(exist = false)
    private String asinCode;



}
