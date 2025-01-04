package com.puxinxiaolin.JUCStudy.Thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 用线程池管理线程
 * @Author: YccLin
 * @Date: 2024/10/14
 */
public class MyThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // 用execute实现
        for (int i = 0; i < 5; i++) {
            executorService.execute(() -> System.out.println("Thread pool task for execute is running"));
        }

        // 用submit实现
        List<Future<?>> futureList = new ArrayList<>();
        AtomicInteger sum = new AtomicInteger();
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            Future<?> submit = executorService.submit(() -> {
                sum.addAndGet(finalI);
                return finalI;
            });
            futureList.add(submit);
        }
        for (Future<?> future : futureList) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("sum = " + sum.get());

        executorService.shutdown();
    }
}
