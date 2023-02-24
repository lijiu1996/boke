package com.lijiawei.pro.boke.bean.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName t_article_tag
 */
@TableName(value ="t_article_tag")
@Data
public class TArticleTag implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 文章id
     */
    private Long articleId;

    /**
     * 类型id
     */
    private Long tagId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}