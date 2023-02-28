package com.lijiawei.pro.boke.controller;

import com.lijiawei.pro.boke.bean.Result;
import com.lijiawei.pro.boke.bean.entity.User;
import com.lijiawei.pro.boke.bean.request.LoginRequest;
import com.lijiawei.pro.boke.bean.request.RegisterRequest;
import com.lijiawei.pro.boke.bean.vo.UserVO;
import com.lijiawei.pro.boke.constant.RedisConstant;
import com.lijiawei.pro.boke.constant.ResultEnum;
import com.lijiawei.pro.boke.service.UserService;
import com.lijiawei.pro.boke.utils.LoginUtil;
import com.lijiawei.pro.boke.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

/**
 * 与权限验证相关接口
 */
@RestController
public class LoginController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    /**
     * 用户登录接口
     *  困难 1. jwt加密工具的使用
     *  困难 2. 如何读取自定义配置 并注入到static 变量中
     *  困难 3. 如何实现拦截器机制
     *
     * @param loginRequest
     * @return
     */
    @PostMapping("/login")
    public Result login(@Valid @RequestBody LoginRequest loginRequest) {
        // 1. 验证用户存在
        User user = userService.getByAccount(loginRequest.getAccount());
        if (user == null)
            return Result.fail(ResultEnum.InvalidArgument).data("用户名不存在");
        // 2. 校验密码是否正确
        String savePassword = DigestUtils.md5DigestAsHex((loginRequest.getPassword() + user.getSalt()).getBytes());
        if (!savePassword.equals(user.getUserPassword()))
            return Result.fail(ResultEnum.InvalidArgument).data("密码不正确");
        // 3. 登录成功, 生成JWT token并返回前端
        String token = LoginUtil.getJWTToken(user.getId());
        // 4. 针对数据查询进行优化 将userId 对应的User信息缓存在redis中
        redisTemplate.opsForValue().set(RedisConstant.Login_User + user.getId(),user,RedisConstant.User_Login_Time, TimeUnit.HOURS);
        return Result.ok().data(token);
    }

    /**
     *  用户注册接口
     *      困难1. 涉及到数据保存 需要加事务,redis连接不上的时候将用户注册回滚 返回注册失败
     * @param registerRequest
     * @return
     */
    @Transactional
    @PostMapping("/register")
    public Result register(@Valid @RequestBody RegisterRequest registerRequest) {
        if (userService.hasAccount(registerRequest.getAccount())) {
            return Result.fail(ResultEnum.InvalidArgument).data("用户名已被注册");
        }
        User user = userService.saveRegisterRequest(registerRequest);
        // 3. 直接登录
        String token = LoginUtil.getJWTToken(user.getId());
        redisTemplate.opsForValue().set(RedisConstant.Login_User + user.getId(), user,RedisConstant.User_Login_Time,TimeUnit.HOURS);
        return Result.ok().data(token);
    }

    @GetMapping("/users/currentUser")
    public Result getUser() {
        User user = ThreadLocalUtil.getUser();
        UserVO userVO = userService.generateUserVO(user);
        return Result.ok().data(userVO);
    }

    @GetMapping("/logout")
    public Result logout() {
        User user = ThreadLocalUtil.getUser();
        redisTemplate.delete(RedisConstant.Login_User + user.getId());
        return Result.ok();
    }
}
