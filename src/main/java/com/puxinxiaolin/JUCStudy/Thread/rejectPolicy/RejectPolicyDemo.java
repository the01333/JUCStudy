package com.puxinxiaolin.JUCStudy.Thread.rejectPolicy;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RejectPolicyDemo {
    public static void main(String[] args) {
        int corePoolSize = 2;
        int maxPoolSize = 4;
        int keepAliveTime = 10;
        TimeUnit unit = TimeUnit.SECONDS;
        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(2);

        ThreadPoolExecutor.AbortPolicy abortPolicy = new ThreadPoolExecutor.AbortPolicy();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize,
                keepAliveTime, unit, queue, abortPolicy);

        for (int i = 0; i < 10; i++) {
//            executor.execute(new Tas(i));
        }
    }
}
