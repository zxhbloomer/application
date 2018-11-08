package com.main;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Slf4j
public class AsyncTask {

    @Async  //加入"异步调用"注解
    public void doTaskOne() throws InterruptedException {
        log.info("开始执行任务一");
        long start = System.currentTimeMillis();
        Thread.sleep(new Random().nextInt(10000));
        long end = System.currentTimeMillis();
        log.info("完成任务一,耗时：" + (end - start) + "毫秒");
    }

    @Async
    public void doTaskTwo() throws InterruptedException {
        log.info("开始执行任务二");
        long start = System.currentTimeMillis();
        Thread.sleep(new Random().nextInt(10000));
        long end = System.currentTimeMillis();
        log.info("完成任务二,耗时：" + (end - start) + "毫秒");
    }

    @Async
    public void doTaskThree() throws InterruptedException {
        log.info("开始执行任务三");
        long start = System.currentTimeMillis();
        Thread.sleep(new Random().nextInt(10000));
        long end = System.currentTimeMillis();
        log.info("完成任务三,耗时：" + (end - start) + "毫秒");
    }
}
