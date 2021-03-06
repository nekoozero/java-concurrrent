package com.nekoo.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author  qxnekoo
 * @date     2018-10-22
 * 与CountDownLatch的区别：
 *     CountDownLatch是有一个线程或多个在其他线程满足条件前等待 countDownLatch.await()  在满足条件后开始执行 计数器不可以循环使用
 *     CyclicBarrier是有多个线程在互相等待 等到所有线程都满足条件了 就可以一起执行下面的语句 计数器可以循环使用
 */
@Slf4j
public class CyclicBarrierExample3 {

    /**
     * 实例化 CyclicBarrier 的时候可以在指定一个Runnable 这个Runnable 将在 await 完之后立即执行 在await下面的语句之前执行
     */
    private static int i = 0;
    private static CyclicBarrier barrier = new CyclicBarrier(5,()->{
        i=i+1;
        log.info("after ready , callback is running {}",i);
    });

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
        barrier.await();
        log.info("{} continue",threadNum);
    }
}
