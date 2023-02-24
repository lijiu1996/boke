package com.lijiawei.pro.boke.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lijiawei.pro.boke.bean.entity.TArticle;
import com.lijiawei.pro.boke.bean.entity.TArticleTag;
import com.lijiawei.pro.boke.bean.entity.TTag;
import com.lijiawei.pro.boke.bean.vo.ArticleVO;
import com.lijiawei.pro.boke.bean.vo.TagVO;
import com.lijiawei.pro.boke.service.TArticleService;
import com.lijiawei.pro.boke.mapper.TArticleMapper;
import com.lijiawei.pro.boke.service.TArticleTagService;
import com.lijiawei.pro.boke.service.TTagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author lijiawei
* @description 针对表【t_article】的数据库操作Service实现
* @createDate 2023-02-23 21:37:03
*/
@Service
public class TArticleServiceImpl extends ServiceImpl<TArticleMapper, TArticle>
    implements TArticleService{

    @Resource
    private TTagService tTagService;

    @Resource
    private TArticleTagService tArticleTagService;
    @Override
    public List<ArticleVO> getArticleByPage(int page, int pageSize) {
        Page<TArticle> pageRes = this.page(new Page<>(page, pageSize));
        if (CollectionUtils.isEmpty(pageRes.getRecords()))
            return null;

        List<ArticleVO> articleList = pageRes.getRecords().stream().map(article -> {
            ArticleVO articleVO = new ArticleVO();
            BeanUtils.copyProperties(article,articleVO);
            articleVO.setAuthor(String.valueOf(article.getAuthorId()));
            articleVO.setCategorys(String.valueOf(article.getCategoryId()));
            articleVO.setTags(new ArrayList<>());
            List<TArticleTag> tArticleTags = tArticleTagService.getByArticleId(article.getId());
            if (!CollectionUtils.isEmpty(tArticleTags)) {
                tArticleTags.stream().forEach(tArticleTag -> {
                    TTag tag = tTagService.getById(tArticleTag.getTagId());
                    TagVO tagVO = new TagVO();
                    BeanUtils.copyProperties(tag,tagVO);
                    articleVO.getTags().add(tagVO);
                });
            }
            return articleVO;
        }).collect(Collectors.toList());
        return articleList;
    }
}




