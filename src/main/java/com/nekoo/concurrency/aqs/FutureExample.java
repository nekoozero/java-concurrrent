package com.nekoo.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author qxnekoo
 */
@Slf4j
public class FutureExample {
    /**
     * Runnable 不能接受参数
     */
    static class MyCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            log.info("do something in callable");
            Thread.sleep(5000);
            return "Done";
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        /**
         * 用Future来接受线程池提交之后的参数  该线程池执行的时候只能用submit 不能用execute
         * submit的参数之前都是实现了Runnable的接口
         */
        Future<String> future = executorService.submit(new MyCallable());
        log.info("do something in main");
        Thread.sleep(1000);
        String result = future.get();
        log.info("result:{}",result);
    }

}
