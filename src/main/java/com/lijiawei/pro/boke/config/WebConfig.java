package com.lijiawei.pro.boke.config;

import com.lijiawei.pro.boke.ext.interceptor.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AuthInterceptor authInterceptor;
    /**
     * Todo 加载拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/users/currentUser")
                .addPathPatterns("/logout")
//                .addPathPatterns("/**")
//                .excludePathPatterns("/login")
//                .excludePathPatterns("/register")
        ;
    }

    /**
     * 配置全局跨域处理
     * @param registry
     */
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                // 放行哪些原始域
////                .allowedOrigins("*")
//                .allowedOriginPatterns("*")
//                // 是否允许证书
//                .allowCredentials(true)
//                // 放行哪些方法
////                .allowedMethods("*")
//                // 放行哪些Header
//                .allowedHeaders("*")
//                // 暴露哪些头信息
//                .exposedHeaders("*")
//                // 允许跨域时间
//                .maxAge(3600)
//                ;
//    }

    /**
     * 困难 用自定义的Filter 代替原有的跨域配置
     * 防止Interceptor中的异常导致 跨域因素未能执行
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*");
        configuration.setAllowCredentials(true);
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.addExposedHeader("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(source);
    }
}
