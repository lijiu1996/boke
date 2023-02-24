package com.lijiawei.pro.boke.bean.request;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class ArticleRequest {

    @Min(1)
    private int page;

    @Min(1)
    private int pageSize;
}
