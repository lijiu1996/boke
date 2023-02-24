package com.lijiawei.pro.boke.service;

import com.lijiawei.pro.boke.bean.entity.Tag;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lijiawei.pro.boke.bean.vo.TagVO;

import java.util.List;

/**
* @author lijiawei
* @description 针对表【t_tag】的数据库操作Service
* @createDate 2023-02-24 20:39:02
*/
public interface TagService extends IService<Tag> {

    List<TagVO> getTagListByArticleId(Long id);
}
