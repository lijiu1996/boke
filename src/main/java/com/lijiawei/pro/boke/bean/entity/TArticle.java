package com.lijiawei.pro.boke.bean.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName t_article
 */
@TableName(value ="t_article")
@Data
public class TArticle implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 博客标题
     */
    private String title;

    /**
     * 博客摘要信息
     */
    private String summary;

    /**
     * 浏览数
     */
    private Integer viewCount;

    /**
     * 评论数
     */
    private Integer reviewCount;

    /**
     * 内容信息
     */
    private Long contentId;

    /**
     * 作者信息
     */
    private Long authorId;

    /**
     * 类别信息
     */
    private Integer categoryId;

    /**
     * 是否置顶
     */
    private Integer weight;

    /**
     * 数据创建时间
     */
    private Date createTime;

    /**
     * 数据更新时间
     */
    private Date updateTime;

    /**
     * 数据删除标记 1-已删除，0-未删除
     */
    @TableLogic
    private Integer isdeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}