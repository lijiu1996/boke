package com.lijiawei.pro.boke.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lijiawei.pro.boke.bean.entity.Article;
import com.lijiawei.pro.boke.bean.vo.ArticleVO;
import com.lijiawei.pro.boke.bean.vo.TagVO;
import com.lijiawei.pro.boke.service.ArticleService;
import com.lijiawei.pro.boke.mapper.ArticleMapper;
import com.lijiawei.pro.boke.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author lijiawei
* @description 针对表【t_article】的数据库操作Service实现
* @createDate 2023-02-24 20:37:22
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{

    @Resource
    private TagService tagService;

    @Override
    public List<ArticleVO> getArticleByPage(int page, int pageSize) {
        Page<Article> pageRes = this.page(new Page<>(page, pageSize));
        if (CollectionUtils.isEmpty(pageRes.getRecords()))
            return null;

        List<ArticleVO> articleList = pageRes.getRecords().stream().map(article -> {
            ArticleVO articleVO = new ArticleVO();
            BeanUtils.copyProperties(article,articleVO);
            articleVO.setAuthor(String.valueOf(article.getAuthorId()));
            articleVO.setCategorys(String.valueOf(article.getCategoryId()));
            articleVO.setTags(tagService.getTagListByArticleId(article.getId()));
            return articleVO;
        }).collect(Collectors.toList());
        return articleList;
    }
}




