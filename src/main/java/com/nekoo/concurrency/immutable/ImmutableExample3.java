package com.nekoo.concurrency.immutable;

import com.google.common.collect.Maps;
import com.nekoo.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Slf4j
@ThreadSafe
public class ImmutableExample3 {
    private static final int i = 1;
    private static final String a = "a";

    private static Map<Integer, Integer> map = Maps.newHashMap();

    static{
        //i=2;
        //a="b";
        //map= Maps.newHashMap();
        map.put(1,2);
        map.put(3,4);
        map.put(5,6);
        //jdk中的不可变  里面的值也不可以发生变化
        map = Collections.unmodifiableMap(map);
    }
       /**但是map不可以指向新的map对象*/
    public static void main(String[] args) {
        /**报错*/
        map.put(1,4);
        log.info("{}",map.get(1));
    }
    /**函数的参数也可以定义为不可变得*/
    public void test(final int i){
        //i=3;
    }
}
