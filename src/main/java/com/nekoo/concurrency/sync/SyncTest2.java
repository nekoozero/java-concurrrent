package com.nekoo.concurrency.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SyncTest2 {
    /*
    synchronized修饰的是类的class对象，即修饰的是类
     */
    public static synchronized void test1(String j){
            for(int i = 0;i<10; i++){
                log.info("test1-{}-{}",i,j);
            }
    }

    public  void test2(String j){
        synchronized (this){
            for(int i = 0;i<10; i++){
                log.info("test1-{}-{}",i,j);
            }
        }
    }

    public static void main(String[] args) {
        SyncTest2 t1 = new SyncTest2();
        SyncTest2 t2 = new SyncTest2();
        SyncTest2 t3 = new SyncTest2();
        ExecutorService es = Executors.newCachedThreadPool();

        es.execute(()->{
            t1.test2("num1");
        });
        es.execute(()->{
            t2.test2("num2");
        });
        es.execute(()->{
            t3.test2("num3");
        });
        es.shutdown();
    }
}

