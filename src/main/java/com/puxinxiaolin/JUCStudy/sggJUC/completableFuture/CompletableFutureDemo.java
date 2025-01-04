package com.puxinxiaolin.JUCStudy.sggJUC.completableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @description: FutureTask
 * @author: YCcLin
 * @date: 2024/12/28
 **/
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(new CallableDemo());
        Thread t1 = new Thread(futureTask, "t1");
        t1.start();

        // 获取 FutureTask 的结果
        System.out.println(futureTask.get());
    }
}

class CallableDemo implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("---come in call()---");
        return "Hello Callable !";
    }
}

//class RunnableDemo implements Runnable {
//
//    @Override
//    public void run() {
//
//    }
//}