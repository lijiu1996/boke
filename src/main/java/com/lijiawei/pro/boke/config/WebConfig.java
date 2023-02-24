package com.lijiawei.pro.boke.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 配置全局跨域处理
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // 放行哪些原始域
//                .allowedOrigins("*")
                .allowedOriginPatterns("*")
                // 是否允许证书
                .allowCredentials(true)
                // 放行哪些方法
                .allowedMethods("*")
                // 放行哪些Header
                .allowedHeaders("*")
                // 暴露哪些头信息
                .exposedHeaders("*")
                // 允许跨域时间
                .maxAge(3600)
                ;
    }
}
