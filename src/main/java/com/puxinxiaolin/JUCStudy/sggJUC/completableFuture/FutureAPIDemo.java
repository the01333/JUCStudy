package com.puxinxiaolin.JUCStudy.sggJUC.completableFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @description: FutureTask 的弊端
 * 1. 容易阻塞 main 线程，需要把 get 放最后执行
 * 2. 也可以手动设置超时时间，超时后不会继续等待异步线程执行完成（会抛异常，所以我们一般手动做校验）
 * @author: YCcLin
 * @date: 2024/12/28
 **/
public class FutureAPIDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            System.out.println(Thread.currentThread().getName() + "\t ------ come in");
            TimeUnit.SECONDS.sleep(5);
            return "task over";
        });
        new Thread(futureTask, "t1").start();

        // 由于线程的执行时间较长，这种情况需要先 get 的情况会阻塞 main 线程
//        System.out.println(futureTask.get());

        System.out.println(Thread.currentThread().getName() + "\t ------忙其他任务了");
//        System.out.println(futureTask.get(3, TimeUnit.SECONDS));

        while (true) {
            if (futureTask.isDone()) {   // 判断 FutureTask 是否完成
                System.out.println(futureTask.get());
                break;
            } else {
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.println("正在处理中...");
            }
        }
    }
}
