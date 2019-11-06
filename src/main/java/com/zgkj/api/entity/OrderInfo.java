package com.zgkj.api.entity;

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
 * @since 2019-11-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("OrderInfo")
public class OrderInfo implements Serializable {

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

    @TableField("AmazonUpdateDate")
    private String AmazonUpdateDate;

    @TableField("AmazonStatus")
    private String AmazonStatus;

    /**
     * 0、下单    1、已下单    2、制作完成   3、等待答复    4、二次制作   5、已完成
     */
    @TableField("Status")
    private Integer Status;

    @TableField("IsTwo")
    private Integer IsTwo;

    private Integer importwaybill;

    @TableField("MarketplaceId")
    private String MarketplaceId;

    @TableField("downPdf")
    private Integer downPdf;

    private Integer expressage;

    @TableField("TypeOfShipping")
    private String TypeOfShipping;

    @TableField("isExpress")
    private Integer isExpress;

    @TableField("OrderIdSer")
    private Integer OrderIdSer;

    @TableField("expressNoSer")
    private Integer expressNoSer;

    @TableField("deliveryTime")
    private String deliveryTime;

    @TableField("amazonCanceledTime")
    private String amazonCanceledTime;

    @TableField("amazonShippedTime")
    private String amazonShippedTime;

    @TableField("CurrencyCode")
    private String CurrencyCode;

    @TableField("isMerge")
    private Integer isMerge;

    @TableField("ToDrawingDate")
    private String ToDrawingDate;

    private String platform;

    @TableField("drawingOverDate")
    private String drawingOverDate;

    @TableField("picMergeDate")
    private String picMergeDate;

    @TableField("WareHouseID")
    private Integer WareHouseID;

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

    @TableField("InOdoo")
    private Integer InOdoo;

    @TableField("PurchaseDate")
    private String PurchaseDate;

    private String accountedTime;

    private Integer virtualOverseasWarehouse;

    private Integer urgent;

    private Integer state;

    private Integer agent;

    private Float agentAmount;


}
