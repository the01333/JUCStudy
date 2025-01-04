package com.puxinxiaolin.JUCStudy.sggJUC.completableFuture;

import java.util.concurrent.*;

public class CompletableFutureAPI2Demo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("111");
            return 1;
        }, threadPool).handle((f, e) -> {
            // 用 thenApply 有异常不能传递，但是 handle 可以继续传递
            int i = 10 / 0;
            System.out.println("222");
            return f + 2;
        }).handle((f, e) -> {
            System.out.println("333");
            return f + 3;
        }).whenComplete((v, e) -> {
            if (e == null) {
                System.out.println("计算完成，结果为：" + v);
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            System.out.println("异常情况：" + e.getCause() + "\t" + e.getMessage());
            return null;
        });

        // 设置等待时间 或者 使用自定义线程池 解决自带线程池自动关闭的问题
//        TimeUnit.SECONDS.sleep(2);
        System.out.println(Thread.currentThread().getName() + "------主线程先去忙其他任务");

        threadPool.shutdown();
    }
}
