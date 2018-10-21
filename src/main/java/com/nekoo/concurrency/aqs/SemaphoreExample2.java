package com.nekoo.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author qxnekoo
 */
@Slf4j
public class SemaphoreExample2 {
    private static Integer threadNum  = 20;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        /**
         * 表示只能有三个许可
         */
        final Semaphore semaphore = new Semaphore(3);
        for(int i = 0; i < threadNum ; i++){
            final int num = i;
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    try {

                        /**
                         * 拿到许可就做  实时内拿不到许可就略过
                         * 比如test方法执行需要一秒 则只有3个线程完成
                         * test方法执行没有时间  很有可能会全部执行完成
                         */
//                        if(semaphore.tryAcquire()){
//                            test(num);
//                            semaphore.release();
//                        }

                        /**
                         * 5秒内获得到许可的线程执行 如果5秒内没有获得到许可 则放弃该操作
                         */
                        if(semaphore.tryAcquire(5000,TimeUnit.MILLISECONDS)){
                            test(num);
                            semaphore.release();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        exec.shutdown();
    }

    public static void test(int threadNum) throws InterruptedException {
        log.info("{}",threadNum);
        Thread.sleep(1000);

    }
}
