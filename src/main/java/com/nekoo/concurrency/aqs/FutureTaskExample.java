package com.nekoo.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author  qxnkeoo
 * 除了实现Future接口外  还实现了Runnable接口
 */
@Slf4j
public class FutureTaskExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>(){

            @Override
            public String call() throws Exception {
                log.info("do something in callable");
                Thread.sleep(5000);
                return "Done";
            }
        });
        /**
         * futureTask类似于一个实现了Runnable接口的类的对象
         */
        new Thread(futureTask).start();
        log.info("do something in main");
        Thread.sleep(1000);
        /**
         * 主线程将会在这边等待结果
         */
        log.info("准备出结果");
        String result = futureTask.get();
        log.info("result:{}",result);
    }

}
