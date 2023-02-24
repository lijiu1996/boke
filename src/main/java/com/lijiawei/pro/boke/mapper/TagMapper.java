package com.lijiawei.pro.boke.mapper;

import com.lijiawei.pro.boke.bean.entity.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lijiawei.pro.boke.bean.vo.HotTagVO;
import com.lijiawei.pro.boke.bean.vo.TagVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author lijiawei
* @description 针对表【t_tag】的数据库操作Mapper
* @createDate 2023-02-24 20:39:02
* @Entity com.lijiawei.pro.boke.bean.entity.Tag
*/
public interface TagMapper extends BaseMapper<Tag> {

    List<TagVO> getTagByArticleId(@Param("article_id") Long id);

    List<HotTagVO> getHottestTag(@Param("limit") int limit);
}




