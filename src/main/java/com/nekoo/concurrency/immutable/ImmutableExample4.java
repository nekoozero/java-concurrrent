package com.nekoo.concurrency.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import java.util.ArrayList;
import java.util.List;

public class ImmutableExample4 {
    /**guava*/
    private final static ImmutableList list = ImmutableList.of(1,2,3);
    private final static ImmutableSet set = ImmutableSet.of(1,2,3);
    private final static ImmutableMap map = ImmutableMap.of(1,2,3,4);

    public static void main(String[] args) {
        /*
        报错
         */
        list.add(1);

    }
}
