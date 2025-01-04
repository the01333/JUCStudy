package com.puxinxiaolin.JUCStudy.heimaJUC.threadSafety.example;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 线程安全问题，使用 synchronized.md 解决
 * synchronized.md: 确保同一时间只有一个对象能访问此代码块
 * @Author: YccLin
 * @Date: 2024/10/26
 */
@Slf4j(topic = "Demo1")
public class Demo1 {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new Lock();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                lock.increment();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                lock.decrement();
            }
        }, "t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.debug("counter: {}", lock.getCounter());
    }
}

/**
 * 优化：使用面向对象的思想而不是直接使用 synchronized.md
 */
class Lock {
    private int counter = 0;

    public synchronized void increment() {
        counter++;
    }

    public synchronized void decrement() {
        counter--;
    }

    public int getCounter() {
        synchronized (this) {
            return counter;
        }
    }
}