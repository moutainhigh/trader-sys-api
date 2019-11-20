package com.zgkj.api.tradeFlow.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
 * @since 2019-11-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LdapUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "uidNumber", type = IdType.AUTO)
    private Long uidNumber;

    /**
     * 账号
     */
    private String uid;

    /**
     * 同步日期
     */
    @TableField("syscDate")
    private Date syscDate;

    private String mail;

    /**
     * 中文名
     */
    private String usernameCn;

    private String password;

    private String salt;


}
