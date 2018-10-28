package com.nekoo.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author qxnekoo
 */
@Slf4j
public class ThreadPoolExample {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
//        for(int i = 0 ; i<10; i++){
//            final int index = i;
//            executorService.execute(new Runnable() {
//                @Override
//                public void run() {
//                    log.info("task:{}",index);
//                }
//            });
//        }


        //延迟三秒执行
//        executorService.schedule(new Runnable() {
//            @Override
//            public void run() {
//                log.warn("schedule run");
//            }
//        },3,TimeUnit.SECONDS);

        executorService.scheduleAtFixedRate(()->{
           log.warn("schedule run");
        },5,3,TimeUnit.SECONDS);   //延迟5秒后执行任务  然后每三秒都会执行  但此时线程池不要shutdown
        //executorService.shutdown();


        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.warn("time run");
            }
        },new Date(),3*1000);
    }
}
