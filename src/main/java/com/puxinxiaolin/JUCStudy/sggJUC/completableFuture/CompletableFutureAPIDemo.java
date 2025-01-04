package com.puxinxiaolin.JUCStudy.sggJUC.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CompletableFutureAPIDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "abc";
        });

//        System.out.println(completableFuture.get());
//        System.out.println(completableFuture.get(2, TimeUnit.SECONDS));
//        System.out.println(completableFuture.join());    // 和 get 用法相同，区别就是 join 不进行检查不抛异常
//        TimeUnit.SECONDS.sleep(4);
//        System.out.println(completableFuture.getNow("d"));   // 如果计算完成直接返回值，否则返回传入的默认值
        System.out.println(completableFuture.complete("completeValue") + "\t"
                + completableFuture.join());   // 如果未计算完成，直接打断并返回传入的默认值
    }
}
