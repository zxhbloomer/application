package com.main;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

import static java.time.LocalDateTime.now;


/**
 * 打印定时任务
 */
@Component
@Slf4j
public class PrintTimer {

//	/** 某个时间段执行
//		 "0 0 12 * * ?"    每天中午十二点触发
//		 "0 15 10 ? * *"    每天早上10：15触发
//		 "0 15 10 * * ?"    每天早上10：15触发
//		 "0 15 10 * * ? *"    每天早上10：15触发
//		 "0 15 10 * * ? 2005"    2005年的每天早上10：15触发
//		 "0 * 14 * * ?"    每天从下午2点开始到2点59分每分钟一次触发
//		 "0 0/5 14 * * ?"    每天从下午2点开始到2：55分结束每5分钟一次触发
//		 "0 0/5 14,18 * * ?"    每天的下午2点至2：55和6点至6点55分两个时间段内每5分钟一次触发
//		 "0 0-5 14 * * ?"    每天14:00至14:05每分钟一次触发
//		 "0 10,44 14 ? 3 WED"    三月的每周三的14：10和14：44触发
//		 "0 15 10 ? * MON-FRI"    每个周一、周二、周三、周四、周五的10：15触发
//	 */
//	@Scheduled(cron = "0 15 16 * * ?")
//	public void timer1Cron() throws Exception {
//		log.info("Timer1-Cron : {}", now());
//	}

//	/**
//	 * 每 60 秒执行
//	 */
//	@Scheduled(fixedRate = 1000 * 60)
//	public void timer2FixedRate() throws Exception {
//		log.info("Timer2-FixedRate : {}",now());
//	}

//	/**
//	 * 上一个任务执行完之后的时间间隔
//	 */
//	@Scheduled(fixedDelay = 1000*5)
//	public void timer3FixedDelay() throws Exception{
//		Thread.sleep(new Random().nextInt(10) * 1000);
//		log.info("Timer3-FixedDelay : {}",now());
//	}

//	/**
//	 * 第一次被调用前的延迟(单位毫秒)
//	 */
//	@Scheduled(initialDelay = 1000 * 10,fixedDelay = 1000 * 2)
//	public void initialDelay()throws Exception{
//		log.info("Timer4-InitialDelay : {}",now());
//	}


    /**
     * 多线程任务测试
     */

//    /**
//     * 任务1
//     */
//    @Scheduled(fixedRate = 1000 * 60 * 100)
//    public void timer2FixedRate() throws Exception {
//        log.info("第一个任务 - 开始");
//        for(int i=0;i<10;i++){
//            System.out.println(Thread.currentThread().getName() + " : 执行中");
//            Thread.sleep(1 * 1000);
//        }
//        log.info("第一个任务 - 结束");
//    }
//
//    /**
//     * 任务2
//     */
//    @Scheduled(fixedRate = 1000 * 60 * 100)
//    public void timer2FixedRate2() throws Exception {
//        log.info("第二个任务 - 开始");
//
//        for(int i=0;i<10;i++){
//            System.out.println(Thread.currentThread().getName() + " : 执行中");
//            Thread.sleep(1 * 1000);
//        }
//
//        log.info("第二个任务 - 结束");
//    }
//
//    /**
//     * 任务3
//     */
//    @Scheduled(fixedRate = 1000 * 60 * 100)
//    public void timer2FixedRate3() throws Exception {
//        log.info("第三个任务 - 开始");
//
//        for(int i=0;i<10;i++){
//            System.out.println(Thread.currentThread().getName() + " : 执行中");
//            Thread.sleep(1 * 1000);
//        }
//
//        log.info("第二个任务 - 结束");
//    }

}
