package com.lijiawei.pro.boke.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lijiawei.pro.boke.bean.entity.Article;
import com.lijiawei.pro.boke.bean.entity.User;
import com.lijiawei.pro.boke.bean.vo.ArticleVO;
import com.lijiawei.pro.boke.service.ArticleService;
import com.lijiawei.pro.boke.mapper.ArticleMapper;
import com.lijiawei.pro.boke.service.TagService;
import com.lijiawei.pro.boke.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
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

    @Resource
    private UserService userService;

    @Override
    public List<ArticleVO> getArticleByPage(int page, int pageSize) {
        Page<Article> pageRes = this.page(new Page<>(page, pageSize));
        if (CollectionUtils.isEmpty(pageRes.getRecords()))
            return null;

        List<ArticleVO> articleList = pageRes.getRecords().stream().map(article -> {
            ArticleVO articleVO = new ArticleVO();
            BeanUtils.copyProperties(article,articleVO);
            User user = userService.getById(article.getAuthorId());
            articleVO.setAuthor(user != null ? user.getUserAccount() : "未知");
            articleVO.setCategorys(String.valueOf(article.getCategoryId()));
            articleVO.setTags(tagService.getTagListByArticleId(article.getId()));
            return articleVO;
        }).collect(Collectors.toList());
        return articleList;
    }

    @Override
    public List<Article> getHotArticles(int limit) {
        LambdaQueryWrapper<Article> lqw = new LambdaQueryWrapper<>();
        lqw.orderByDesc(Article::getReviewCount);
        lqw.select(Article::getId,Article::getTitle);
        lqw.last("limit" + limit);
        return list(lqw);
    }
}




