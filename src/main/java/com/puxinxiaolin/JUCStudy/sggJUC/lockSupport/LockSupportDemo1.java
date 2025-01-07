package com.puxinxiaolin.JUCStudy.sggJUC.lockSupport;

import java.util.concurrent.TimeUnit;

/**
 * @description: Object.wait() 和 Object.notify() 要成对使用，并且要在同步代码块中
 * @author: YCcLin
 * @date: 2025/1/5
 **/
public class LockSupportDemo1 {
    public static void main(String[] args) {
        Object object = new Object();

        new Thread(() -> {
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + "\t ----come in");
                try {
                    object.wait();   // 会释放锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t ----被唤醒");
            }
        }, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            synchronized (object) {
                object.notify();
                System.out.println(Thread.currentThread().getName() + "\t ----发出通知");
            }
        }, "t2").start();
    }
}
