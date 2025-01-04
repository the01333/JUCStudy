package com.puxinxiaolin.JUCStudy.Thread.callbackThreadAsynchronousTask;

import java.util.concurrent.CompletableFuture;

/**
 * @Description: CompletableFuture
 * @Author: YccLin
 * @Date: 2024/10/15
 */
public class CallbackThreadAsynchronousTaskDemo3 {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> {
            return "Task Result";
        }).thenAccept(result -> {
            System.out.println("Task completed with Result: " + result);
        });
    }
}
