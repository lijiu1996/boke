package com.lijiawei.pro.boke.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lijiawei.pro.boke.bean.Result;
import com.lijiawei.pro.boke.bean.entity.Article;
import com.lijiawei.pro.boke.bean.vo.*;
import com.lijiawei.pro.boke.constant.ResultEnum;
import com.lijiawei.pro.boke.mapper.ArticleMapper;
import com.lijiawei.pro.boke.service.*;
import com.lijiawei.pro.boke.utils.ApplicationContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
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
@Slf4j
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

    @Resource
    private ThreadPoolTaskExecutor executor;

    @Override
    public List<ArticleVO> getArticleByPage(int page, int pageSize) {
        Page<Article> pageRes = this.page(new Page<>(page, pageSize));
        if (CollectionUtils.isEmpty(pageRes.getRecords()))
            return null;
        List<ArticleVO> articleList = pageRes.getRecords().stream().map(article -> {
            ArticleVO articleVO = copy(article,true,true,false,false);
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
        ApplicationContextUtil.getBean(this.getClass()).updateViewContent(article);
        ArticleVO articleVO = copy(article,true,true,true,true);
        return Result.ok().data(articleVO);
    }

    /**
     *  异步更新浏览量 高并发的几种情况讨论
     *  1.  mysql乐观锁 -- 低并发下有效 高访问量下可能会造成数据丢失
     *  2.  直接用synchronize -- 锁粒度产生问题 需要把查询和update锁定在一起
     *  3.
     * @param article
     */
    @Async
    public void updateViewContent(Article article) {
        LambdaUpdateWrapper<Article> lqw = new LambdaUpdateWrapper<>();
        lqw.eq(Article::getId, article.getId());
//            lqw.eq(Article::getViewCount, article.getViewCount());
//            lqw.set(Article::getViewCount, (article.getViewCount() + 1));
        lqw.setSql("view_count = view_count + 1");
        this.update(lqw);

//        try {
//            TimeUnit.SECONDS.sleep(5);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        log.info("更新数据库评论数量完成...");
    }

    // 实现Article的自定义组装
    public ArticleVO copy(Article article, boolean tag, boolean user, boolean category, boolean content) {
        ArticleVO articleVO = new ArticleVO();
        BeanUtils.copyProperties(article,articleVO);
        if (tag) {
            List<TagVO> tagVOList = tagService.getTagListByArticleId(article.getId());
            articleVO.setTags(tagVOList);
        }
        if (user) {
            String username = userService.getById(article.getAuthorId()).getUsername();
            articleVO.setAuthor(username);
        }
        if (category) {
            CategoryVO categoryVO = categoryService.getVOById(article.getCategoryId());
            articleVO.setCategory(categoryVO);
        }
        if (content) {
            ContentVO contentVO = articleContentService.getVOById(article.getContentId());
            articleVO.setBody(contentVO);
        }
        return articleVO;
    }
}




