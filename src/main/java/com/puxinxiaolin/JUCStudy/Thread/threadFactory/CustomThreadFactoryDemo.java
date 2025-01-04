package com.puxinxiaolin.JUCStudy.Thread.threadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomThreadFactoryDemo {
    public static void main(String[] args) {
        CustomThreadFactory threadFactory = new CustomThreadFactory("CustomThreadPool", false, Thread.NORM_PRIORITY);

        ExecutorService executorService = Executors.newFixedThreadPool(5, threadFactory);

        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                String name = Thread.currentThread().getName();
                System.out.println("Hello from " + name);
            });
        }

        executorService.shutdown();
    }
}
