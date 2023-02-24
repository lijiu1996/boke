package com.lijiawei.pro.boke.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lijiawei.pro.boke.bean.entity.TArticleTag;
import com.lijiawei.pro.boke.service.TArticleTagService;
import com.lijiawei.pro.boke.mapper.TArticleTagMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author lijiawei
* @description 针对表【t_article_tag】的数据库操作Service实现
* @createDate 2023-02-23 21:37:23
*/
@Service
public class TArticleTagServiceImpl extends ServiceImpl<TArticleTagMapper, TArticleTag>
    implements TArticleTagService{


    @Override
    public List<TArticleTag> getByArticleId(Long id) {
        LambdaQueryWrapper<TArticleTag> lqw = new LambdaQueryWrapper<>();
        lqw.eq(TArticleTag::getArticleId, id);
        return list(lqw);
    }
}




