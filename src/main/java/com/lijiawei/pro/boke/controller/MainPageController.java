package com.lijiawei.pro.boke.controller;

import com.lijiawei.pro.boke.bean.Result;
import com.lijiawei.pro.boke.bean.request.ArticleRequest;
import com.lijiawei.pro.boke.bean.vo.ArticleVO;
import com.lijiawei.pro.boke.service.TArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Null;
import java.util.List;

/**
 * @author Li JiaWei
 * @ClassName: MainPageController
 * @Description:
 * @Date: 2023/2/23 16:47
 * @Version: 1.0
 */
@RestController
public class MainPageController {

    @Resource
    private TArticleService tArticleService;

    @PostMapping("/articles")
    private Result getArticleList(@Valid @RequestBody ArticleRequest articleRequest) {
        List<ArticleVO> articles = tArticleService.getArticleByPage(articleRequest.getPage(),articleRequest.getPageSize());
        return Result.ok().data(articles);
    }

}
