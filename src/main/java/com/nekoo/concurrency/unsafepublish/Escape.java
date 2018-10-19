package com.nekoo.concurrency.unsafepublish;

import com.nekoo.concurrency.annoations.NotRecommend;
import com.nekoo.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotRecommend
@NotThreadSafe
/*
对象逸出
 */
public class Escape {
    private int thisCanBeEscape = 0;
    public Escape(){
        new InnerClass();
    }
    private class InnerClass {
        public InnerClass(){
           log.info("这是{}",Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
