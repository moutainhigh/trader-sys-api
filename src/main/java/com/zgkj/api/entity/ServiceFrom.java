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
 * @since 2019-11-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ServiceFrom")
public class ServiceFrom implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;
    //订单编号
    @TableField("OrderID")
    private String OrderID;
    //创建时间
    @TableField("CreateTime")
    private String CreateTime;
    //售后编号
    @TableField("ServiceID")
    private String ServiceID;
    //产品编号
    @TableField("ProductID")
    private String ProductID;
    //问题处理状态   1：未处理，2：已处理，3：退款，4：撤回，5:驳回
    @TableField("State")
    private Integer State;
    //售后原因   1：质量问题，2：退货，3：产品发错，4：客户未收到，5：稿件错误，6：漏发
    @TableField("Reason")
    private String Reason;
    //责任方  0：无定责， 1：仓库，2：生产，3：美术，4：业务员，5：公司，6：下单组
    @TableField("Resp")
    private Integer Resp;
    //是否删除   0：未删除，1：已删除
    @TableField("IsDel")
    private Integer IsDel;
    //售后描述
    @TableField("Description")
    private String Description;
    //售后图片
    @TableField("Image")
    private String Image;
    //是否重发   0：不重发，1：待重发，2：已重发
    @TableField("Repeat")
    private Integer Repeat;

    @TableField("UserID")
    private Integer UserID;

    @TableField("Courier")
    private Integer Courier;

    @TableField("CourierType")
    private String CourierType;
    //收件人国家
    @TableField("ReCountry")
    private String ReCountry;

    @TableField("OID")
    private Integer oid;

    @TableField("proId")
    private Integer proId;

    @TableField("outboundId")
    private Integer outboundId;

    @TableField("appealstatus")
    private Integer appealstatus;

    @TableField(exist = false)
    private String ShopSecName;//店铺名称
    @TableField(exist = false)
    private String NickName;//店铺负责人名称
    @TableField(exist = false)
    private String Country;//所属国家
    @TableField(exist = false)
    private String Price;//交易金额
    @TableField(exist = false)
    private String picture;//产品图片
    @TableField(exist = false)
    private String picUrl;//产品链接
    @TableField(exist = false)
    private String ASIN;
    @TableField(exist = false)
    private String TotalPrice;
    @TableField(exist = false)
    private String orderMemo;
    @TableField(exist = false)
    private String TheExpressNo;



}
