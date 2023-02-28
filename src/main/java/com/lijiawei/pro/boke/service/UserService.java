package com.lijiawei.pro.boke.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lijiawei.pro.boke.bean.entity.User;
import com.lijiawei.pro.boke.bean.request.RegisterRequest;
import com.lijiawei.pro.boke.bean.vo.UserVO;

/**
* @author lijiawei
* @description 针对表【t_user】的数据库操作Service
* @createDate 2023-02-24 20:39:06
*/
public interface UserService extends IService<User> {

    boolean hasAccount(String account);

    User saveRegisterRequest(RegisterRequest registerRequest);

    User getByAccount(String account);

    UserVO generateUserVO(User user);
}
