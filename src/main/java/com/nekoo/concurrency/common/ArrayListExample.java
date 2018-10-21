package com.nekoo.concurrency.common;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author qxnekoo
 */

@Slf4j
public class ArrayListExample {
    /**
     * 请求总数
     */
    public static int clientTotal = 5000;
    /**
     * 同时并发执行的线程数
     */
    public static int threadTotal = 200;

    public static List<Integer> list = new ArrayList<>();
    public static Vector<Integer> vector = new Vector<>();
    public static Set<Integer> set = new HashSet<>();
    public static Map<Integer,Integer> map = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i = 0; i < clientTotal ; i++) {
            final int j = i;
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    update(j);
                    semaphore.release();
                } catch (Exception e) {
                    log.error("{}",e);
                }
                countDownLatch.countDown();
            });

        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("list的长度：{} || vector的长度为：{} || set的长度为：{} || map的长度为{}",list.size(),vector.size(),set.size(),map.size());
    }

    private static void update(int i) {
        list.add(1);
        vector.add(1);
        map.put(i,1);
        set.add(i);
    }

}
