package com.lijiawei.pro.boke.service;

import com.lijiawei.pro.boke.bean.entity.ArticleContent;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lijiawei.pro.boke.bean.vo.ContentVO;

/**
* @author lijiawei
* @description 针对表【t_article_content】的数据库操作Service
* @createDate 2023-03-01 09:26:39
*/
public interface ArticleContentService extends IService<ArticleContent> {

    ContentVO getVOById(Long contentId);
}
