package com.lijiawei.pro.boke.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lijiawei.pro.boke.bean.entity.TArticle;
import com.lijiawei.pro.boke.bean.vo.ArticleVO;

import java.util.List;

/**
* @author lijiawei
* @description 针对表【t_article】的数据库操作Service
* @createDate 2023-02-23 21:37:03
*/
public interface TArticleService extends IService<TArticle> {

    List<ArticleVO> getArticleByPage(int page, int pageSize);
}
