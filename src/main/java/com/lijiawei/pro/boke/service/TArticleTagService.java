package com.lijiawei.pro.boke.service;

import com.lijiawei.pro.boke.bean.entity.TArticleTag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author lijiawei
* @description 针对表【t_article_tag】的数据库操作Service
* @createDate 2023-02-23 21:37:23
*/
public interface TArticleTagService extends IService<TArticleTag> {


    List<TArticleTag> getByArticleId(Long id);
}
