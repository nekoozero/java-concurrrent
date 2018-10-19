package com.nekoo.concurrency.signleton;

import com.nekoo.concurrency.annoations.NotRecommend;
import com.nekoo.concurrency.annoations.ThreadSafe;

/*
懒汉模式
线程安全的
 */
@ThreadSafe
@NotRecommend
public class SingletonExample3 {
    //私有构造函数
    private SingletonExample3(){

    }
    //单例对象
    private static SingletonExample3 singletonExample = null;

    //静态的工厂方法   synchronized开销较大
    public static synchronized SingletonExample3 getInstance(){
        if(singletonExample ==null){
            singletonExample  = new SingletonExample3();
        }
        return singletonExample;
    }
}
