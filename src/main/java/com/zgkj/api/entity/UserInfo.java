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
 * @since 2019-11-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("UserInfo")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @TableField("UserName")
    private String UserName;

    @TableField("Password")
    private String Password;

    @TableField("AuthorityID")
    private String AuthorityID;

    @TableField("NickName")
    private String NickName;

    @TableField("Status")
    private Integer Status;

    @TableField("groupId")
    private Integer groupId;

    @TableField("Leader")
    private Integer Leader;

    @TableField("printerId")
    private Integer printerId;

    private String adName;


}
