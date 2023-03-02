package com.lijiawei.pro.boke.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lijiawei.pro.boke.bean.entity.Comment;
import com.lijiawei.pro.boke.bean.vo.AuthorVO;
import com.lijiawei.pro.boke.bean.vo.CommentVO;
import com.lijiawei.pro.boke.mapper.CommentMapper;
import com.lijiawei.pro.boke.service.CommentService;
import com.lijiawei.pro.boke.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author lijiawei
* @description 针对表【t_comment】的数据库操作Service实现
* @createDate 2023-03-01 20:41:41
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

    @Autowired
    private UserService userService;

    // 定义comment支持两层架构
    private static final int CommentLevelTop = 1;

    private static final int CommentLevelChildren = 2;

    @Override
    public List<CommentVO> getCommentsByArticleId(Long articleId) {
        return getCommentsByIdAndLevel(articleId, CommentLevelTop);
    }

    // 根据level决定是否需要递归
    private List<CommentVO> getCommentsByIdAndLevel(Long id, Integer level) {
        List<Comment> comments = null;
        if (level == CommentLevelTop)
            comments = selectCommentByArticleIdAndTopLevel(id, CommentLevelTop);
        else if (level == CommentLevelChildren)
            comments = selectChildCommentByParentId(id);
        List<CommentVO> commentVOList = new ArrayList<>();
        for (Comment comment : comments) {
            CommentVO commentVO = new CommentVO();
            BeanUtils.copyProperties(comment,commentVO);
            AuthorVO author = userService.getAuthorById(comment.getAuthorId());
            commentVO.setAuthor(author);
            AuthorVO toUser = userService.getAuthorById(comment.getToUid());
            commentVO.setToUser(toUser);
            if (level == CommentLevelTop) {
                commentVO.setChildrens(getCommentsByIdAndLevel(comment.getId(),CommentLevelChildren));
            } else if (level == CommentLevelChildren)
                commentVO.setChildrens(null);
            commentVOList.add(commentVO);
        }
        return commentVOList;
    }

    private List<Comment> selectChildCommentByParentId(Long parentId) {
        LambdaQueryWrapper<Comment> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Comment::getParentId,parentId);
        List<Comment> list = this.list(lqw);
        return list;
    }

    private List<Comment> selectCommentByArticleIdAndTopLevel(Long articleId, Integer level) {
        LambdaQueryWrapper<Comment> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Comment::getArticleId,articleId);
        lqw.eq(Comment::getLevel,level);
        List<Comment> list = this.list(lqw);
        return list;
    }
}




