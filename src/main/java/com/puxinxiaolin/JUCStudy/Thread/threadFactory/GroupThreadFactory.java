package com.puxinxiaolin.JUCStudy.Thread.threadFactory;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 扩展了线程归类到特定的组中
 * @Author: YccLin
 * @Date: 2024/10/14
 */
public class GroupThreadFactory extends CustomThreadFactory {
    private final ThreadGroup group;
    private String namePrefix;
    private AtomicInteger threadNumber = new AtomicInteger(1);
    private Boolean daemon;
    private int priority;

    public GroupThreadFactory(ThreadGroup group, String namePrefix, Boolean daemon, int priority) {
        super(namePrefix, daemon, priority);
        this.group = group;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(group, r, namePrefix + "-thread-" + threadNumber.getAndIncrement());
        thread.setDaemon(daemon);
        thread.setPriority(priority);
        return thread;
    }
}
