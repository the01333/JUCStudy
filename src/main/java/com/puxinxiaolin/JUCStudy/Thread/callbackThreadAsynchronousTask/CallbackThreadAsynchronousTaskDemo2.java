package com.puxinxiaolin.JUCStudy.Thread.callbackThreadAsynchronousTask;

import java.util.concurrent.*;

/**
 * @Description: CompletionService
 * @Author: YccLin
 * @Date: 2024/10/15
 */
public class CallbackThreadAsynchronousTaskDemo2 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        CompletionService<String> completionService = new ExecutorCompletionService<>(executor);

        Callable<String> task = () -> {
            // 执行任务
            return "Task Result";
        };

        completionService.submit(task);

        // 获取并处理任务结果
        try {
            Future<String> future = completionService.take(); // 阻塞直到有任务完成
            String result = future.get();
            // 任务完成后的回调处理
            System.out.println("Task completed with result: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
