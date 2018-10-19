package com.nekoo.concurrency.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SyncTest {

    //作用于对象  即不同对象的执行是不会互相阻塞 只有同一个对象调用两次这个方法才会阻塞
    public void test1(String j){
        synchronized (this){
            for(int i = 0;i<10; i++){
                log.info("test1-{}-{}",i,j);
            }
        }
    }
    //子类继承的test2是没有synchronized  作用于对象
    public synchronized void test2(String j){
        for(int i = 0;i<10; i++){
            log.info("test1-{}-{}",i,j);
        }
    }
    public static void main(String[] args) {
        SyncTest t1 = new SyncTest();
        SyncTest t2 = new SyncTest();
        SyncTest t3 = new SyncTest();
        SyncTest t4 = new SyncTest();

        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(new Runnable() {
            @Override
            public void run() {
                t1.test1("num1");
            }
        });
        es.execute(new Runnable() {
            @Override
            public void run() {
                t2.test1("num2");
            }
        });
        es.execute(new Runnable() {
            @Override
            public void run() {
                t3.test1("num3");
            }
        });
        es.execute(new Runnable(){
            @Override
            public void run() {
                t4.test1("num4");
            }
        });
        es.shutdown();
    }
}

