package com.lijiawei.pro.boke.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lijiawei.pro.boke.bean.entity.User;
import com.lijiawei.pro.boke.bean.request.RegisterRequest;
import com.lijiawei.pro.boke.bean.vo.UserVO;
import com.lijiawei.pro.boke.mapper.UserMapper;
import com.lijiawei.pro.boke.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
* @author lijiawei
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2023-02-24 20:39:06
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean hasAccount(String account) {
        return getByAccount(account) != null;
    }

    @Override
    public User getByAccount(String account) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUserAccount,account);
        return getOne(lqw);
    }

    @Override
    public UserVO generateUserVO(User user) {
        UserVO userVO = new UserVO();
        userVO.setId(user.getId());
        userVO.setAccount(user.getUserAccount());
        userVO.setNickname(user.getUsername());
        userVO.setAvatar(user.getAvatar());
        return userVO;
    }

    @Override
    public User saveRegisterRequest(RegisterRequest registerRequest) {
        User user = new User();
        user.setUserAccount(registerRequest.getAccount());
        String salt = RandomUtil.randomString(6);
        user.setSalt(salt);
        user.setUsername(registerRequest.getNickname());
        user.setUserPassword(DigestUtils.md5DigestAsHex((registerRequest.getPassword()+salt).getBytes()));
        this.save(user);
        return user;
    }



}




