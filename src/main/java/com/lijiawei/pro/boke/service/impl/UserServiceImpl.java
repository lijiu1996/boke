package com.lijiawei.pro.boke.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lijiawei.pro.boke.bean.entity.User;
import com.lijiawei.pro.boke.service.UserService;
import com.lijiawei.pro.boke.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author lijiawei
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2023-02-24 20:39:06
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




