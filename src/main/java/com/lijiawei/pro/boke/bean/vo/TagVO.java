package com.lijiawei.pro.boke.bean.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TagVO {

    /**
     *  tag编号
     */
    private Long id;

    /**
     *
     */
    private String avatar;

    /**
     *  tag名称
     */
    private String tagName;
}
