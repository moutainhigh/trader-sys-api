package com.zgkj.api.tradeFlow.entity;

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
 * @since 2019-11-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Outboundinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 产品编号
     */
    @TableField("productId")
    private Integer productId;

    /**
     * 订单关联号
     */
    @TableField("systemNumber")
    private String systemNumber;

    /**
     * 产品名称
     */
    @TableField("productName")
    private String productName;

    /**
     * 产品中文名称
     */
    @TableField("productCnName")
    private String productCnName;

    /**
     * asin码
     */
    @TableField("asinCode")
    private String asinCode;

    /**
     * 分类名称
     */
    @TableField("proClassName")
    private String proClassName;

    /**
     * 价格
     */
    private Double price;

    /**
     * 实收
     */
    @TableField("realPrice")
    private Double realPrice;

    /**
     * 产品URL
     */
    @TableField("proUrl")
    private String proUrl;

    /**
     * 产品图片
     */
    @TableField("imageName")
    private String imageName;

    /**
     * 效果图
     */
    @TableField("factoryImage")
    private String factoryImage;

    @TableField("thisnewfactoryImage")
    private String thisnewfactoryImage;

    /**
     * 完成日期
     */
    @TableField("overTime")
    private String overTime;

    private Integer state;

    /**
     * 备注
     */
    private String remark;

    /**
     * 销售数量
     */
    @TableField("outNumber")
    private Integer outNumber;

    /**
     * 出单日期
     */
    @TableField("saleDate")
    private String saleDate;

    @TableField("operationUserId")
    private String operationUserId;

    @TableField("proClassID")
    private Integer proClassID;

    @TableField("propertyID")
    private Integer propertyID;

    @TableField("propertyName")
    private String propertyName;

    /**
     * 是否重发
     */
    @TableField("isResend")
    private Integer isResend;

    /**
     * 生产图片地址URL
     */
    @TableField("producePicUrl")
    private String producePicUrl;

    @TableField("isDeal")
    private Integer isDeal;

    @TableField("outboundInfoId")
    private Integer outboundInfoId;

    private Integer accessory;


}
