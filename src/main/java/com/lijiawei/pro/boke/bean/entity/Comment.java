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
 * @TableName t_comment
 */
@TableName(value ="t_comment")
@Data
public class Comment implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 评论内容 有字数限制
     */
    private String content;

    /**
     * 文章id
     */
    private Long articleId;

    /**
     * 评论作者id
     */
    private Long authorId;

    /**
     * 父级评论id
     */
    private Long parentId;

    /**
     * 回复对象
     */
    private Long toUid;

    /**
     * 评论层级
     */
    private Integer level;

    /**
     * 创建时间
     */
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}