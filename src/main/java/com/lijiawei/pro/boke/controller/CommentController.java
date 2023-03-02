package com.lijiawei.pro.boke.controller;

import com.lijiawei.pro.boke.bean.Result;
import com.lijiawei.pro.boke.bean.vo.CommentVO;
import com.lijiawei.pro.boke.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 查询评论列表功能
     *  攻克: 如何递归返回2层结构
     * @param articleId
     * @return
     */
    @GetMapping("/article/{id}")
    public Result getArticleComments(@PathVariable("id") Long articleId) {
        List<CommentVO> commentVO = commentService.getCommentsByArticleId(articleId);
        return Result.ok().data(commentVO);
    }
}
