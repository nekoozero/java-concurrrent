package com.nekoo.concurrency.unsafepublish;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
/*
发布对象
 */
@Slf4j
public class Unpublish {
    //私有域的应用  线程可以利用这个引用修改数据
    private String[] states = {"a","b","c"};

    //public方法
    public String[] getStates(){
        return states;
    }

    public static void main(String[] args) {
        Unpublish u = new Unpublish();
        log.info("{}", Arrays.toString(u.getStates()));

        u.getStates()[0] = "nekoo";
        log.info("{}",Arrays.toString(u.getStates()));
    }
}

