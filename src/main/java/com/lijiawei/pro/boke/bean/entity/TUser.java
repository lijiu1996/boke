package com.lijiawei.pro.boke.bean.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName t_user
 */
@TableName(value ="t_user")
@Data
public class TUser implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private String userAccount;

    /**
     * 
     */
    private String userPassword;

    /**
     * 加密盐
     */
    private String salt;

    /**
     * 昵称
     */
    private String username;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 0-普通用户 1-管理员
     */
    private Integer isManager;

    /**
     * 状态
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删除标记 0-未删除
     */
    private Integer isdeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}