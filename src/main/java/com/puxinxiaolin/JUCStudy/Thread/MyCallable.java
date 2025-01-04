package com.puxinxiaolin.JUCStudy.Thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Description: 实现Callable来创建线程，并用FutureTask来管理返回结果，启动线程同样也要包裹在Thread中
 * @Author: YccLin
 * @Date: 2024/10/14
 */
public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "MyCallable is running";
    }

    public static void main(String[] args) {
        MyCallable myCallable = new MyCallable();
        FutureTask<String> futureTask = new FutureTask<>(myCallable);
        Thread thread = new Thread(futureTask);
        thread.start();

        try {
            System.out.println("Result: " + futureTask.get());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
