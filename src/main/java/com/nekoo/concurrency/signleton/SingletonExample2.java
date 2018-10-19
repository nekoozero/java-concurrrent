package com.nekoo.concurrency.signleton;

/*
饿汉模式
单例实例在类装载时进行创建
线程安全
 */
public class SingletonExample2 {
    //私有构造方法
    private SingletonExample2(){

    }
    //静态单例对象
    private static SingletonExample2 singletonExample2 = new SingletonExample2();
    //静态方法
    public static SingletonExample2 getInstance(){
        return  singletonExample2;
    }
}
