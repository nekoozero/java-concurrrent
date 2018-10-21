package com.nekoo.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author qxnekoo
 */
@Slf4j
public class CountDownLatchExample {
    private static Integer threadNum  = 100;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        for(int i = 0; i < threadNum ; i++){
            final int num = i;
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        test(num);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        countDownLatch.countDown();
                    }
                }
            });
        }
        /**
         * 第一个参数是时间 ，第二个参数是单位 意思是在10毫秒之后 将会打开闭锁开关 执行下面的语句
         */
        countDownLatch.await(101,TimeUnit.MILLISECONDS);
        log.info("finish");
        exec.shutdown();
    }

    public static void test(int threadNum) throws InterruptedException {
        Thread.sleep(100);
        log.info("{}",threadNum);
    }
}
