package com.puxinxiaolin.JUCStudy.sggJUC.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class InterruptDemo {
    static volatile boolean isStop = false;
    static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public static void main(String[] args) {
//        m1Volatile();

//        m2AtomicBoolean();

        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + "\t isInterrupted()为true，程序停止");
                    break;
                }
                System.out.println(Thread.currentThread().getName() + "\t ----hello isInterrupted api!!!");
            }
        }, "t1");
        t1.start();

        System.out.println("t1的默认中断标志位: " + t1.isInterrupted());

        try {
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            t1.interrupt();   // 发送中断协商的申请
            System.out.println(Thread.currentThread().getName() + "\t interrupt()方法调用, isInterrupt()为true");
        }, "t2").start();
        // 这种自己中断的情况也是可以的
//        t1.interrupt();
    }

    private static void m2AtomicBoolean() {
        new Thread(() -> {
            while (true) {
                if (atomicBoolean.get()) {
                    System.out.println(Thread.currentThread().getName() + "\t atomicBoolean被修改为true，程序停止");
                    break;
                }
                System.out.println(Thread.currentThread().getName() + "\t ----hello atomicBoolean!!!");
            }
        }, "t1").start();

        try {
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            atomicBoolean.set(true);
        }, "t2").start();
    }

    private static void m1Volatile() {
        new Thread(() -> {
            while (true) {
                if (isStop) {
                    System.out.println(Thread.currentThread().getName() + "\t isStop被修改为true，程序停止");
                    break;
                }
                System.out.println(Thread.currentThread().getName() + "\t ----hello volatile!!!");
            }
        }, "t1").start();

        try {
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            isStop = true;
        }, "t2").start();
    }
}
