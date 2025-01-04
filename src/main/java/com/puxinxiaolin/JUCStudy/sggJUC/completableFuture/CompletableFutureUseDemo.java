package com.puxinxiaolin.JUCStudy.sggJUC.completableFuture;

import java.util.concurrent.*;

/**
 * @description: 使用 CompletableFuture 避免原先 FutureTask 的阻塞（无需手动 get 获取结果）
 * @author: YCcLin
 * @date: 2024/12/28
 **/
public class CompletableFutureUseDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        try {
            CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread().getName() + "---come in");
                int result = ThreadLocalRandom.current().nextInt(10);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("---1秒后出结果: " + result);
                // 模拟异常情况
//                if (result > 5) {
//                    int i = 10 / 0;
//                }
                return result;
            }, threadPool).whenComplete((v, e) -> {    // 执行完自动回调
                if (e == null) {
                    System.out.println("---计算完成, 更新系统value: " + v);
                }
            }).exceptionally(e -> {     // 有异常走下面
                e.printStackTrace();
                System.out.println("异常情况: " + e.getCause() + "\t" + e.getMessage());
                return null;
            });

            System.out.println(Thread.currentThread().getName() + "线程先去忙其他任务");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
