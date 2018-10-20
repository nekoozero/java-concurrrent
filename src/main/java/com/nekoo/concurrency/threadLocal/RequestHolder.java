package com.nekoo.concurrency.threadLocal;

public class RequestHolder {
    /**
     * 内部存放了一个map map的键是线程的id 值是这个Long类型的值（在这个例子中）
     */
    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    public static void add(Long id) {
        requestHolder.set(id);
    }
    public static Long getId() {
        return requestHolder.get();
    }
    public static void remove(){
        requestHolder.remove();
    }
}
