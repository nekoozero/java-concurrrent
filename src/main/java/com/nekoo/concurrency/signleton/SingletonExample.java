package com.nekoo.concurrency.signleton;

/*
懒汉模式
线程不安全的
 */
public class SingletonExample {
    //私有构造函数
    private SingletonExample(){

    }
    //单例对象
    private static SingletonExample singletonExample = null;

    //静态的工厂方法
    public static SingletonExample getInstance(){
        if(singletonExample ==null){
            singletonExample  = new SingletonExample();
        }
        return singletonExample;
    }
}
