package com.lijiawei.pro.boke.controller;

import com.lijiawei.pro.boke.bean.Result;
import com.lijiawei.pro.boke.bean.vo.ArticleVO;
import com.lijiawei.pro.boke.service.TArticleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.Min;
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
    private Result getArticleList(@Min(1) Integer page,@Min(1) int pageSize) {
        List<ArticleVO> articles = tArticleService.getArticleByPage(page,pageSize);
        return Result.ok().data(articles);
    }

}
