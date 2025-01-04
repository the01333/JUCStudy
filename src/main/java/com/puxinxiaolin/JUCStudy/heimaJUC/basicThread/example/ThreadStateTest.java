package com.puxinxiaolin.JUCStudy.heimaJUC.basicThread.example;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadStateTest {
    public static void main(String[] args) {
        // t1: 并未开始任务 -> NEW
        Thread t1 = new Thread(() -> log.info("running"), "t1");

        // t2: 死循环 -> TIMED_WAITING
        Thread t2 = new Thread(() -> {
            while (true) {

            }
        }, "t2");
        t2.start();

        // t3: 正常运行 -> TERMINATED
        Thread t3 = new Thread(() -> log.info("running"), "t3");
        t3.start();

        // t4: TIMED_WAITING
        Thread t4 = new Thread(() -> {
            synchronized (ThreadStateTest.class) {
                try {
                    Thread.sleep(1000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t4");
        t4.start();

        // t5: t2是死循环 -> WAITING
        Thread t5 = new Thread(() -> {
            try {
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t5");
        t5.start();

        // t6: t4先给对象加锁，导致都对同一个对象加锁 -> blocked
        Thread t6 = new Thread(() -> {
            synchronized (ThreadStateTest.class) {
                try {
                    Thread.sleep(1000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t6");
        t6.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("t1 state:{}", t1.getState());
        log.info("t2 state:{}", t2.getState());
        log.info("t3 state:{}", t3.getState());
        log.info("t4 state:{}", t4.getState());
        log.info("t5 state:{}", t5.getState());
        log.info("t6 state:{}", t6.getState());
    }
}
