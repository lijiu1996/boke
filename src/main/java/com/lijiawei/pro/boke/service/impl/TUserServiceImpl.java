package com.lijiawei.pro.boke.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lijiawei.pro.boke.bean.entity.TUser;
import com.lijiawei.pro.boke.service.TUserService;
import com.lijiawei.pro.boke.mapper.TUserMapper;
import org.springframework.stereotype.Service;

/**
* @author lijiawei
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2023-02-23 20:02:42
*/
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser>
    implements TUserService{

}




