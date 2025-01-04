package com.puxinxiaolin.JUCStudy.Thread.callbackThreadAsynchronousTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @Description: FutureTask
 * @Author: YccLin
 * @Date: 2024/10/15
 */
public class CallbackThreadAsynchronousTaskDemo4 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        FutureTask<String> futureTask = new FutureTask<String>(() -> {
            return "Task Result";
        }) {
            @Override
            protected void done() {
                try {
                    String result = (String) get();
                    System.out.println("Task completed with result: " + result);
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        };

        executor.submit(futureTask);
    }
}
