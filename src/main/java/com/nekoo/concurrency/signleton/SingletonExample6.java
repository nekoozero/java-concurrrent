package com.nekoo.concurrency.signleton;

/*
饿汉模式
单例实例在类装载时进行创建
线程安全
 */
public class SingletonExample6 {
    //私有构造方法
    private SingletonExample6(){

    }
   //静态属性与静态块之间的顺序不能颠倒  否则singletonExample2为null
    private static SingletonExample6 singletonExample6 = null;

    //是按顺序执行的  与普通定义的静态方法和属性是不一致的
    static{
        singletonExample6 = new SingletonExample6();
    }
    //单例对象

  //静态的工厂方法
    public static SingletonExample6 getInstance(){
        return  singletonExample6;
    }

    public static void main(String[] args) {
        System.out.println(singletonExample6);
    }
}
