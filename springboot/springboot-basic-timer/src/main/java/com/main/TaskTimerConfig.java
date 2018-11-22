package com.main;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;

/**
 * 由于定时任务不是异步执行的(必须要等到上一个任务执行完成之后才会继续后面的任务),需要配置定时任务多线程执行
 */
@Configuration
public class TaskTimerConfig implements SchedulingConfigurer, AsyncConfigurer {

    /** 异步处理 */
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar){
        TaskScheduler taskScheduler = taskScheduler();
        taskRegistrar.setTaskScheduler(taskScheduler);
    }

    /** 定时任务多线程处理 */
    @Bean(destroyMethod = "shutdown")
    public ThreadPoolTaskScheduler taskScheduler(){
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        //线程池数量
        scheduler.setPoolSize(20);
        //线程名称前缀设置
        scheduler.setThreadNamePrefix("task-");
        //设置等待终止秒
        scheduler.setAwaitTerminationSeconds(60);
        //设置在关机时等待任务完成
        scheduler.setWaitForTasksToCompleteOnShutdown(true);
        return scheduler;
    }

    /** 异步处理 */
    public Executor getAsyncExecutor(){
        Executor executor = taskScheduler();
        return executor;
    }

    /** 异步处理 异常 */
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler(){
        return new SimpleAsyncUncaughtExceptionHandler();
    }
}
