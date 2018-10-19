package com.nekoo.concurrency.signleton;

import com.nekoo.concurrency.annoations.ThreadSafe;

@ThreadSafe
/*
枚举模式 是最安全的单例模式
 */
public class SingletonExample7 {
    //私有构造方法
    private SingletonExample7(){

    }

    public static SingletonExample7 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }
    private enum Singleton{
        INSTANCE;

        private SingletonExample7 singleton;

        //JVM保证这个方法绝对只调用一次
        Singleton(){
            singleton = new SingletonExample7();
        }
        public SingletonExample7 getInstance() {
            return singleton;
        }
    }
}
