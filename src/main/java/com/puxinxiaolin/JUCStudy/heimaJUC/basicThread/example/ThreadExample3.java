package com.puxinxiaolin.JUCStudy.heimaJUC.basicThread.example;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Description: FutureTask implements RunnableFuture（extends Runnable），可以获取执行结果
 * @Author: YccLin
 * @Date: 2024/10/25
 */
@Slf4j(topic = "ThreadExample3")
public class ThreadExample3 {
    public static void main(String[] args) {
        FutureTask<Integer> task = new FutureTask<>(() -> {
            log.info("running");
            Thread.sleep(1000);
            return 50000;
        });

        new Thread(task).start();
        try {
            Integer result = task.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
