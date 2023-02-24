package com.lijiawei.pro.boke.controller;

import com.lijiawei.pro.boke.bean.Result;
import com.lijiawei.pro.boke.bean.entity.Article;
import com.lijiawei.pro.boke.bean.request.ArticleRequest;
import com.lijiawei.pro.boke.bean.vo.ArticleVO;
import com.lijiawei.pro.boke.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Li JiaWei
 * @ClassName: MainPageController
 * @Description:
 * @Date: 2023/2/23 16:47
 * @Version: 1.0
 */
@RequestMapping("/articles")
@RestController
public class ArticleController {

    @Resource
    private ArticleService articleService;

    /**
     * 首页文章列表
     * @param articleRequest
     * @return
     */
    @PostMapping
    private Result getArticleList(@Valid @RequestBody ArticleRequest articleRequest) {
        List<ArticleVO> articles = articleService.getArticleByPage(articleRequest.getPage(),articleRequest.getPageSize());
        return Result.ok().data(articles);
    }

    /**
     * 最热文章
     */
    @PostMapping("/hot")
    private Result getHottestArticle() {
        int limit = 2;
        List<Article> hotArticles = articleService.getHotArticles(limit);
        return Result.ok().data(hotArticles);
    }
}
