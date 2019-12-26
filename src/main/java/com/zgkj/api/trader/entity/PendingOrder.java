package com.zgkj.api.trader.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("PendingOrder")
public class PendingOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("ID")
    private Integer id;

    @TableField("UserID")
    private Integer UserID;

    @TableField("SystemNumber")
    private String SystemNumber;

    @TableId("OrderID")
    private String OrderID;

    @TableField("SaleDate")
    private String SaleDate;

    @TableField("StopDate")
    private String StopDate;

    @TableField("Addressee")
    private String Addressee;

    @TableField("Address")
    private String Address;

    @TableField("Country")
    private String Country;

    @TableField("Province")
    private String Province;

    @TableField("City")
    private String City;

    @TableField("ZipCode")
    private String ZipCode;

    @TableField("Phone")
    private String Phone;

    @TableField("Email")
    private String Email;

    @TableField("OutboundGoodsID")
    private String OutboundGoodsID;

    @TableField("OrderState")
    private Integer OrderState;

    @TableField("TotalPrice")
    private String TotalPrice;

    @TableField("OrderMemo")
    private String OrderMemo;

    @TableField("TheSingleCost")
    private String TheSingleCost;

    @TableField("OrderFreight")
    private String OrderFreight;

    @TableField("TheExpressNo")
    private String TheExpressNo;

    @TableField("SettlementRate")
    private String SettlementRate;

    @TableField("TheSingleProfit")
    private String TheSingleProfit;

    @TableField("ISPrior")
    private Integer ISPrior;

    @TableField("UpdateTime")
    private String UpdateTime;

    @TableField("OrderWeight")
    private String OrderWeight;

    @TableField("DeliveryStatus")
    private Integer DeliveryStatus;

    @TableField("OrderRemarks")
    private Integer OrderRemarks;

    @TableField("ShopID")
    private Integer ShopID;

    @TableField("IsInform")
    private Integer IsInform;

    @TableField("IsDistribution")
    private Integer IsDistribution;

    @TableField("NoteTtheBatchNo")
    private String NoteTtheBatchNo;

    @TableField("IsProduction")
    private Integer IsProduction;

    @TableField("IsRepeat")
    private Integer IsRepeat;

    @TableField("ClientID")
    private String ClientID;

    @TableField("IsProblem")
    private Integer IsProblem;

    @TableField("ShopKey")
    private String ShopKey;

    @TableField("AmazonUpdateDate")
    private String AmazonUpdateDate;

    @TableField("AmazonStatus")
    private String AmazonStatus;

    @TableField("CurrencyCode")
    private String CurrencyCode;

    private String platform;

    @TableField("ExternalTransactionID")
    private String ExternalTransactionID;

    @TableField("ExternalTransactionTime")
    private String ExternalTransactionTime;

    @TableField("FeeOrCreditAmount")
    private String FeeOrCreditAmount;

    @TableField("ExternalTransactionStatus")
    private String ExternalTransactionStatus;

    @TableField("PaymentOrRefundAmount")
    private String PaymentOrRefundAmount;

    @TableField("FulfillmentChannel")
    private String FulfillmentChannel;

    @TableField("PurchaseDate")
    private String PurchaseDate;


}
