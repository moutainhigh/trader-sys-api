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
 * @since 2019-12-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("RegionInfo")
public class RegionInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @TableField("RegionAbbr")
    private String RegionAbbr;

    @TableField("RegionEghName")
    private String RegionEghName;

    @TableField("RegionChnName")
    private String RegionChnName;

    @TableField("ParentID")
    private Integer ParentID;

    @TableField("ParentName")
    private String ParentName;

    @TableField("MarketplaceId")
    private String MarketplaceId;

    @TableField("exchangeRate")
    private String exchangeRate;

    private String website;

    @TableField("currencyCode")
    private String currencyCode;


}
