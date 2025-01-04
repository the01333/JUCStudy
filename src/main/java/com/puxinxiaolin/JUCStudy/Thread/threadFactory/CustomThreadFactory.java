package com.puxinxiaolin.JUCStudy.Thread.threadFactory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 自定义线程工厂
 * @Author: YccLin
 * @Date: 2024/10/14
 */
public class CustomThreadFactory implements ThreadFactory {
    private AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;
    private final Boolean daemon;
    private final int priority;

    public CustomThreadFactory(String namePrefix, Boolean daemon, int priority) {
        this.namePrefix = namePrefix;
        this.daemon = daemon;
        this.priority = priority;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r, namePrefix + "-thread-" + threadNumber.getAndIncrement());
        thread.setPriority(priority);
        thread.setDaemon(daemon);
        return thread;
    }
}
