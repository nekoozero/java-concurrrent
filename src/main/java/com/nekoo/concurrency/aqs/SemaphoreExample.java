package com.nekoo.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author qxnekoo
 */
@Slf4j
public class SemaphoreExample {
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
                        semaphore.acquire(3);
                        test(num);
                        semaphore.release(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        exec.shutdown();
    }

    public static void test(int threadNum) throws InterruptedException {
        Thread.sleep(1000);
        log.info("{}",threadNum);
    }
}
