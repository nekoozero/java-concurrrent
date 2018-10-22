package com.nekoo.concurrency.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author qxnekoo
 */
@Slf4j
public class LockExample6 {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        /**
         * 这里的lock 会尝试将此线程放入等待队列中  由于没有前列node  所以此线程将会立马执行输入wait signal
         * condition.await() 将会把此线程 放入到condition队列中等待
         *
         * 下面的线程执行完毕之后 该线程从condition.await()处恢复执行
         *
         * 最后解锁
         */
        new  Thread(()->{
           try{
               reentrantLock.lock();
               log.info("wait signal");
               condition.await();
               log.info("after await");
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           log.info("get signal");
           reentrantLock.unlock();
        }).start();

        /***
         * 由于前面一个线程在执行 所以 该线程一直AQS队列中在等待  知道上一个线程被移到condition队列中 该线程开始执行 输出2
         * 利用condition.signal()唤醒一个线程（唤醒的线程重新放入AQS队列中） 输出 send signal 解锁 该线程全部执行完毕
         */
        new Thread(()->{
            reentrantLock.lock();
            log.info("2");
            try{
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            condition.signal();
            log.info("send signal~~~");
            reentrantLock.unlock();
        }).start();
    }
}
