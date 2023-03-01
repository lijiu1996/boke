package com.lijiawei.pro.boke.bean.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 返回前端的博客信息
 */
@Data
public class ArticleVO {

    /**
     * 主键
     */
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
    @JsonProperty("viewCounts")
    private Integer viewCount;

    /**
     * 评论数
     */
    @JsonProperty("commentCounts")
    private Integer reviewCount;

    /**
     * 内容信息
     */
    private ContentVO body;

    /**
     * 作者信息
     */
    private String author;

    /**
     * 类别信息
     */
    private CategoryVO category;

    /**
     * 数据创建时间
     */
    private Date createTime;

    /**
     *  博客tag信息
     */
    private List<TagVO> tags;
}
