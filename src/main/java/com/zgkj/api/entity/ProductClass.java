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
 * @since 2019-11-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ProductClass")
public class ProductClass implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;// 编号

    @TableField("ClassifyName")
    private String ClassifyName;// 分类名称

    @TableField("ParentID")
    private Integer ParentID;// 父类ID

    @TableField("UserID")
    private Integer UserID;//用户编号

    @TableField("Category")
    private Integer Category;//类别

    @TableField("classEngName")
    private String classEngName;//英文分类名称

    @TableField("Cost")
    private String Cost;//成本


}
