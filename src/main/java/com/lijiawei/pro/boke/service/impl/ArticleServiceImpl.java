package com.lijiawei.pro.boke.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lijiawei.pro.boke.bean.Result;
import com.lijiawei.pro.boke.bean.entity.Article;
import com.lijiawei.pro.boke.bean.entity.User;
import com.lijiawei.pro.boke.bean.vo.*;
import com.lijiawei.pro.boke.constant.ResultEnum;
import com.lijiawei.pro.boke.mapper.ArticleMapper;
import com.lijiawei.pro.boke.service.*;
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

    @Resource
    private ArticleContentService articleContentService;

    @Resource
    private CategoryService categoryService;

    @Override
    public List<ArticleVO> getArticleByPage(int page, int pageSize) {
        Page<Article> pageRes = this.page(new Page<>(page, pageSize));
        if (CollectionUtils.isEmpty(pageRes.getRecords()))
            return null;

        List<ArticleVO> articleList = pageRes.getRecords().stream().map(article -> {
            ArticleVO articleVO = new ArticleVO();
            BeanUtils.copyProperties(article,articleVO);
            User user = userService.getById(article.getAuthorId());
            articleVO.setAuthor(user != null ? user.getUsername() : "未知");
            articleVO.setCategory(null);
            articleVO.setTags(tagService.getTagListByArticleId(article.getId()));
            return articleVO;
        }).collect(Collectors.toList());
        return articleList;
    }

    @Override
    public List<Article> getHotArticles(int limit) {
        LambdaQueryWrapper<Article> lqw = new LambdaQueryWrapper<>();
        lqw.orderByDesc(Article::getViewCount);
        lqw.select(Article::getId,Article::getTitle);
        lqw.last("limit " + limit);
        return list(lqw);
    }

    @Override
    public List<Article> getNewArticles(int limit) {
        LambdaQueryWrapper<Article> lqw = new LambdaQueryWrapper<>();
        lqw.orderByDesc(Article::getCreateTime);
        lqw.select(Article::getId,Article::getTitle);
        lqw.last("limit " + limit);
        return list(lqw);
    }

    @Override
    public List<ArticleArchiveVO> getArticleArchives() {
        return baseMapper.getArticleArchives();
    }

    @Override
    public Result getArticleById(Long id) {
        Article article = getById(id);
        if (article == null) {
            return Result.fail(ResultEnum.NotFound).data("查询的文档id不在");
        }
        ArticleVO articleVO = new ArticleVO();
        BeanUtils.copyProperties(article,articleVO);
        List<TagVO> tagVO = tagService.getTagListByArticleId(id);
        articleVO.setTags(tagVO);
        User user = userService.getById(article.getAuthorId());
        articleVO.setAuthor(user.getUsername());
        CategoryVO categoryVO = categoryService.getVOById(article.getCategoryId());
        articleVO.setCategory(categoryVO);
        ContentVO contentVO = articleContentService.getVOById(article.getContentId());
        articleVO.setBody(contentVO);
        return Result.ok().data(articleVO);
    }
}




