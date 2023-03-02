package com.lijiawei.pro.boke.bean.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CommentVO {

    private Long id;

    private AuthorVO author;

    private String content;

    private List<CommentVO> childrens;

    @JsonProperty("createDate")
    private Date createTime;

    private Integer level;

    private AuthorVO toUser;
}
