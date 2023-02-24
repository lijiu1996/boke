package com.lijiawei.pro.boke.ext.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * 测试一下SpringBoot 自定义Filter的使用机制
 */
@WebFilter(filterName = "TimeFilter",
        initParams = {@WebInitParam(name = "testParam",value = "hello")},
        urlPatterns = "/*"
)
@Slf4j
public class TestFilter implements Filter {

    private String testParam;
    public void init(FilterConfig config) throws ServletException {
        testParam = config.getInitParameter("testParam");
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        long start = System.currentTimeMillis();
        chain.doFilter(request, response);
        long end = System.currentTimeMillis();
        log.info("本次请求处理时间: {}ms",end-start);
    }
}
