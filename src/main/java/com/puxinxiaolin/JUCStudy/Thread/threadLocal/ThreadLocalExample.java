package com.puxinxiaolin.JUCStudy.Thread.threadLocal;

import java.lang.ref.WeakReference;

public class ThreadLocalExample<T> {
//    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    // 设置初始值
    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);

    public static void main(String[] args) {
//        System.out.println(threadLocal.get());
//        threadLocal.set(42);
//        System.out.println(threadLocal.get());
//        threadLocal.remove();
//        System.out.println(threadLocal.get());

        // 多线程场景
        Runnable task = () -> {
            for (int i = 0; i < 5; i++) {
                Integer value = threadLocal.get();
                threadLocal.set(value + 1);
                System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());
            }
        };

        Thread t1 = new Thread(task, "Thread-t1");
        Thread t2 = new Thread(task, "Thread-t2");

        t1.start();
        t2.start();
    }
}
