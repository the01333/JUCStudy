package com.puxinxiaolin.JUCStudy.Thread.callbackThreadAsynchronousTask;

import java.util.concurrent.*;

/**
 * @Description: Future + Callable
 * @Author: YccLin
 * @Date: 2024/10/15
 */
public class CallbackThreadAsynchronousTaskDemo1 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        Callable<String> task = () -> {
            return "Task Result";
        };
        Future<String> future = executor.submit(task);

        while (!future.isDone()) {
            // 其他事情
        }

        try {
            String result = future.get();
            System.out.println("Task completed with result: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
