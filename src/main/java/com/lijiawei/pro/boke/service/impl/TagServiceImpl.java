package com.lijiawei.pro.boke.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lijiawei.pro.boke.bean.entity.Tag;
import com.lijiawei.pro.boke.bean.vo.TagVO;
import com.lijiawei.pro.boke.service.TagService;
import com.lijiawei.pro.boke.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author lijiawei
* @description 针对表【t_tag】的数据库操作Service实现
* @createDate 2023-02-24 20:39:02
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService{

    @Override
    public List<TagVO> getTagListByArticleId(Long id) {
        return baseMapper.getTagByArticleId(id);
    }
}




