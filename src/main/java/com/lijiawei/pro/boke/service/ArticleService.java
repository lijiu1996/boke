package com.lijiawei.pro.boke.service;

import com.lijiawei.pro.boke.bean.Result;
import com.lijiawei.pro.boke.bean.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lijiawei.pro.boke.bean.vo.ArticleArchiveVO;
import com.lijiawei.pro.boke.bean.vo.ArticleVO;

import java.util.List;

/**
* @author lijiawei
* @description 针对表【t_article】的数据库操作Service
* @createDate 2023-02-24 20:37:22
*/
public interface ArticleService extends IService<Article> {

    List<ArticleVO> getArticleByPage(int page, int pageSize);

    List<Article> getHotArticles(int i);

    List<Article> getNewArticles(int limit);

    List<ArticleArchiveVO> getArticleArchives();

    Result getArticleById(Long id);
}
