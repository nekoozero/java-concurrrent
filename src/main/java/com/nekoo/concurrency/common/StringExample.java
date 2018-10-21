package com.nekoo.concurrency.common;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author qxnekoo
 * 虽然StringBuffer是线程安全的  但由于同一时间只能有一个线程来操作StringBuffer 所以 效率不高
 * 但在大多数情况下 我们使用String类型的时候是在方法内部定义的 此时String类型方法内部的局部变量（线程封闭） 不存在线程不安全的问题
 * 长字符串使用StringBuilder 短的使用String
 */

@Slf4j
public class StringExample {
    /**
     * 请求总数
     */
    public static int clientTotal = 5000;
    /**
     * 同时并发执行的线程数
     */
    public static int threadTotal = 200;

    public static  StringBuilder stringBuilder = new StringBuilder();
    /**
     * StringBuilder是线程不安全的 StringBuffer是线程安全的 里面的方法都用了synchronized 来进行修饰
     * String是线程安全的 因为他是final修饰的
     */

    public static StringBuffer stringBuffer  = new StringBuffer();


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i = 0; i < clientTotal ; i++) {
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    update();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("{}",e);
                }
                countDownLatch.countDown();
            });

        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("stringBuilder.size:{},stringBuffer.size:{}",stringBuilder.length(),stringBuffer.length());
    }

    private static void update() {
        stringBuilder.append("1");
        stringBuffer.append("1");
    }

}
