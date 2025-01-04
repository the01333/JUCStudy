package com.puxinxiaolin.JUCStudy.sggJUC.completableFuture;

import java.awt.color.CMMException;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureAPI3Demo {
    public static void main(String[] args) {
//        CompletableFuture.supplyAsync(() -> {
//            return 1;
//        }).thenApply(f -> {
//            return f + 2;
//        }).thenApply(f -> {
//            return f + 3;
//        }).thenAccept(r -> System.out.println("结果：" + r));

        /**
         * 1. thenRun 任务 A 执行完执行 B，并且 B 不需要 A 的结果
         * 2. thenAccept 任务 A 执行完执行 B，并且 B 需要 A 的结果，无返回值
         * 3. thenApply 任务 A 执行完执行 B，并且 B 需要 A 的结果，有返回值
         */
        System.out.println("情况1");
        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenRun(() -> {}).join());

        System.out.println("情况2");
        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenAccept(System.out::println).join());

        System.out.println("情况3");
        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenApply(f -> {
            System.out.println(f);
            return f + "resultB";
        }).join());
    }
}
