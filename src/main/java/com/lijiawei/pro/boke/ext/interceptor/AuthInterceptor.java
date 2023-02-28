package com.lijiawei.pro.boke.ext.interceptor;

import cn.hutool.http.ContentType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lijiawei.pro.boke.bean.Result;
import com.lijiawei.pro.boke.bean.entity.User;
import com.lijiawei.pro.boke.constant.RedisConstant;
import com.lijiawei.pro.boke.constant.ResultEnum;
import com.lijiawei.pro.boke.utils.LoginUtil;
import com.lijiawei.pro.boke.utils.RedisUtil;
import com.lijiawei.pro.boke.utils.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 实现难点 如何在拦截器里直接返回异常数据
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler chosen handler to execute, for type and/or instance evaluation
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        log.debug("=================request start===========================");
        String requestURI = request.getRequestURI();
        log.debug("request uri:{}",requestURI);
        log.debug("request method:{}",request.getMethod());
        log.debug("token:{}", token);
        log.debug("=================request end===========================");

        if (StringUtils.isBlank(token)) {
            writeError(response,"Header中未包含身份信息");
            return false;
        }
        Long userId = LoginUtil.resolveTokenToUserId(token);
        if (userId == null) {
            writeError(response,"JWT Token校验失败");
            return false;
        }
        User user = RedisUtil.getCacheObject(RedisConstant.Login_User + userId);
        if (user == null) {
            writeError(response, "用户未登录");
            return false;
        }
        // 刷新登陆时间
        RedisUtil.setExpireTime(RedisConstant.Login_User + userId, RedisConstant.User_Login_Time, TimeUnit.HOURS);
        ThreadLocalUtil.saveUser(user);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalUtil.removeUser();
    }

    /**
     * 给前台返回异常结果
     */
    private void writeError(HttpServletResponse response, String error) throws IOException {
        Result data = Result.fail(ResultEnum.NotLogin).data(error);
        response.setContentType(ContentType.build(ContentType.JSON, Charset.defaultCharset()));
        response.getWriter().print(objectMapper.writeValueAsString(data));
    }
}
