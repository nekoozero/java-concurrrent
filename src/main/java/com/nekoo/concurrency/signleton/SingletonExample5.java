package com.nekoo.concurrency.signleton;

import com.nekoo.concurrency.annoations.NotThreadSafe;

/*
懒汉模式-》双重同步锁实例模式

线程不安全的
 */
@NotThreadSafe
public class SingletonExample5 {
    //私有构造函数
    private SingletonExample5(){

    }

    //cpu相关
    //1. memory = allocate() 分配对象的内存空间
    //2. ctorInstance() 初始化对象
    //3. instance = memory 设置instance指向刚分配的内存

    //jmm和cpu进行指令重排序（2，3两步并没有数据依赖性  因此可能会进行重排序）
    //1. memory = allocate() 分配对象的内存空间
    //3. instance = memory 设置instance指向刚分配的内存
    //2. ctorInstance() 初始化对象



    //单例对象
    //volatile将会禁止指令重排 上面的情况也就不会出现了
    private static volatile SingletonExample5 singletonExample = null;

    //静态的工厂方法
    public static SingletonExample5 getInstance(){
        if(singletonExample ==null){       //双重检测机制           //B
            synchronized (SingletonExample5.class){   //同步锁
                if(singletonExample==null){
                    singletonExample  = new SingletonExample5();    //A - 3
                }
            }
        }
        return singletonExample;
    }
}
