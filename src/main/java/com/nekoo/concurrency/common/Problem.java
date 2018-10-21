package com.nekoo.concurrency.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Problem {
    private static  List<Integer> list = new ArrayList<>();
    private static void test(List<Integer> list){
        for (Integer item:list) {
            list.remove(item);
        }
    }

    public static void main(String[] args) {
        list.add(1);
        list.add(2);
        list.add(3);

        test2(list);
        for (Integer i:list
             ) {
            System.out.println(i);
        }
    }
    /**
    推荐写法
     */
    private static void test2(List list){
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            Integer i = iterator.next();
            if(i.equals(3)){
                iterator.remove();

            }
        }
    }
    /**
     不推荐写法 会报错
     */
    private static void test3(List list){
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            Integer i = iterator.next();
            if(i.equals(3)){
                list.remove(i);

            }
        }
    }
}
