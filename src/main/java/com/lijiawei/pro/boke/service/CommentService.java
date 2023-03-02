package com.lijiawei.pro.boke.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lijiawei.pro.boke.bean.entity.Comment;
import com.lijiawei.pro.boke.bean.vo.CommentVO;

import java.util.List;

/**
* @author lijiawei
* @description 针对表【t_comment】的数据库操作Service
* @createDate 2023-03-01 20:41:41
*/
public interface CommentService extends IService<Comment> {

    List<CommentVO> getCommentsByArticleId(Long articleId);
}
