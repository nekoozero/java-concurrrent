package com.nekoo.concurrency.aqs;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @author  qxnkeoo
 *
 * 把大任务分割成小任务 把小任务的结果汇总后的到大任务的结果
 *
 * 工作窃取算法：
 *   为了减少线程之间的竞争，把这些子任务分别放到不同的队列中，并为每个队列创建一个单独的线程来执行队列里的
 *   任务，线程和队列一一对应。有的线程会把自己队列里的任务干完，而其他线程还没有做完，那么他们就回访问同一个
 *   队列，通常会使用双端队列，被窃取任务线程永远从双端队列的头部拿任务执行，而窃取任务线程永远从双端队列尾部
 *   拿任务执行
 *
 *   优点  利用线程进行计算 减少了线程间的竞争
 *   缺点 双端队列中只有一个任务时 还是有竞争；会消耗更多的资源 创建了多个线程和多个队列
 */
public class CountTask extends RecursiveTask<Integer> {
    /**
     * y阈值
     */
    private static final int THRESHOLD = 5 ;
    private int start ;
    private int end;
    public CountTask(int start,int end){
        this.start = start;
        this.end = end;
    }
    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canCompute = (end - start)<=THRESHOLD;
        if(canCompute) {
            for(int i = start; i<=end; i++) {
                sum+=i;
            }
        }else{
            //如果任务大于阈值，就分裂成两个子任务计算
            int middle = (start + end) / 2;
            CountTask leftTask = new CountTask(start,middle);
            CountTask rightTask = new CountTask(middle+1,end);
            //执行子任务
            leftTask.fork();
            rightTask.fork();
            //等待子任务执行完，并得到其结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();
            //合并子任务
            sum = leftResult+rightResult;
        }
        return sum;



    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        //生成一个计算任务 ，负责计算
        CountTask task = new CountTask(1,100);
        //执行一个任务
        Future<Integer> result = forkJoinPool.submit(task);
        try{
            System.out.println(result.get());
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
