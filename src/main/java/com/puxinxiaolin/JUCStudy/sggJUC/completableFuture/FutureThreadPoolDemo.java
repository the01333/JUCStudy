package com.puxinxiaolin.JUCStudy.sggJUC.completableFuture;

import java.util.concurrent.*;

/**
 * @description: 线程池 + FutureTask
 * @author: YCcLin
 * @date: 2024/12/28
 **/
public class FutureThreadPoolDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 3个任务，开启多个异步线程，耗时多少？

        // 定义线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        long start = System.currentTimeMillis();

        // 任务1
        FutureTask<String> futureTask1 = new FutureTask<>(() -> {
            TimeUnit.MILLISECONDS.sleep(500);
            return "task1 over";
        });
        // 这种 new 的方式可以用线程池的方式去代替
//        new Thread(futureTask, "t1").start();
        threadPool.submit(futureTask1);

        // 任务2
        FutureTask<String> futureTask2 = new FutureTask<>(() -> {
            TimeUnit.MILLISECONDS.sleep(300);
            return "task2 over";
        });
        threadPool.submit(futureTask2);

        System.out.println(futureTask1.get());
        System.out.println(futureTask2.get());

        // 任务3（main线程）
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("耗费时间为：" + (end - start) + "ms");

        System.out.println(Thread.currentThread().getName() + "\t ------end");
        threadPool.shutdown();
    }
}
