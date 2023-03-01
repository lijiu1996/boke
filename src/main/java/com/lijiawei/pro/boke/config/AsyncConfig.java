package com.lijiawei.pro.boke.config;

import org.springframework.aop.interceptor.AsyncExecutionAspectSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name = AsyncExecutionAspectSupport.DEFAULT_TASK_EXECUTOR_BEAN_NAME)
    public ThreadPoolTaskExecutor asyncServiceExecutor() {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 当前系统核数
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        // 配置核心线程数
        executor.setCorePoolSize(10);
        // 配置最大线程数
        executor.setMaxPoolSize(50);
        // 配置任务队列大小
        executor.setQueueCapacity(500);
        // 配置回收时间
        executor.setKeepAliveSeconds(60);
        // 配置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 配置线程前缀以支持跟踪
        executor.setThreadNamePrefix("myAsyncServiceExecutor-");
        // 线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 如果超过这个时间段任务还没有执行完就强制销毁
        executor.setAwaitTerminationSeconds(60);
        executor.initialize();
        return executor;
    }
}
