package com.lijiawei.pro.boke.mapper;

import com.lijiawei.pro.boke.bean.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lijiawei.pro.boke.bean.vo.ArticleArchiveVO;

import java.util.List;

/**
* @author lijiawei
* @description 针对表【t_article】的数据库操作Mapper
* @createDate 2023-02-24 20:37:22
* @Entity com.lijiawei.pro.boke.bean.entity.Article
*/
public interface ArticleMapper extends BaseMapper<Article> {

    List<ArticleArchiveVO> getArticleArchives();
}




