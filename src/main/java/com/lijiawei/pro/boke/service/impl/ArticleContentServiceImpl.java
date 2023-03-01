package com.lijiawei.pro.boke.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lijiawei.pro.boke.bean.entity.ArticleContent;
import com.lijiawei.pro.boke.bean.vo.ContentVO;
import com.lijiawei.pro.boke.service.ArticleContentService;
import com.lijiawei.pro.boke.mapper.ArticleContentMapper;
import org.springframework.stereotype.Service;

/**
* @author lijiawei
* @description 针对表【t_article_content】的数据库操作Service实现
* @createDate 2023-03-01 09:26:39
*/
@Service
public class ArticleContentServiceImpl extends ServiceImpl<ArticleContentMapper, ArticleContent>
    implements ArticleContentService{

    @Override
    public ContentVO getVOById(Long contentId) {
        ArticleContent content = this.getById(contentId);
        ContentVO contentVO = new ContentVO();
        contentVO.setContent(content.getContent());
        return contentVO;
    }
}




