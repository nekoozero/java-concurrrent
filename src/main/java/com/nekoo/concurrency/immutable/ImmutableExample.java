package com.nekoo.concurrency.immutable;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ImmutableExample {
    private static final int i = 1;
    private static final String a = "a";
    private static final Map<Integer, Integer> map = Maps.newHashMap();

    static{
        //i=2;
        //a="b";
        //map= Maps.newHashMap();
        map.put(1,2);
        map.put(3,4);
        map.put(5,6);
    }
    /**但是map不可以指向新的map对象  但是可以修改map里面的值*/
    public static void main(String[] args) {
        map.put(1,4);
        log.info("{}",map.get(1));
    }
    /**函数的参数也可以定义为不可变得*/
    public void test(final int i){
        //i=3;
    }
}
