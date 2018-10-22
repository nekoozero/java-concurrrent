package com.nekoo.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author  qxnekoo
 * @date     2018-10-22
 * 与CountDownLatch的区别：
 *     CountDownLatch是有一个线程或多个在其他线程满足条件前等待 countDownLatch.await()  在满足条件后开始执行 计数器不可以循环使用
 *     CyclicBarrier是有多个线程在互相等待 等到所有线程都满足条件了 就可以一起执行下面的语句 计数器可以循环使用
 */
@Slf4j
public class CyclicBarrierExample2 {

    private static CyclicBarrier barrier = new CyclicBarrier(5);

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();

        for(int i = 0; i < 10;i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            executorService.execute(()->{
                try {
                    test(threadNum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }

    public static void test(int threadNum) throws InterruptedException, BrokenBarrierException {
        Thread.sleep(1000);
        log.info("{} is ready",threadNum);
        try {
            barrier.await(1000,TimeUnit.MILLISECONDS);
        } catch (Exception e) {
           log.warn("{}",e);
        }
        /**注意：
         * 如果不用try catch来捕获异常  则下面的语句将不会被执行
         */
        log.info("{} continue",threadNum);
    }
}
