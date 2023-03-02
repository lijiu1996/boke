package com.lijiawei.pro.boke.mapper;

import com.lijiawei.pro.boke.bean.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lijiawei.pro.boke.bean.vo.AuthorVO;
import org.apache.ibatis.annotations.Param;

/**
* @author lijiawei
* @description 针对表【t_user】的数据库操作Mapper
* @createDate 2023-02-24 20:39:06
* @Entity com.lijiawei.pro.boke.bean.entity.User
*/
public interface UserMapper extends BaseMapper<User> {

    AuthorVO getAuthorById(@Param("authorId") Long authorId);
}




