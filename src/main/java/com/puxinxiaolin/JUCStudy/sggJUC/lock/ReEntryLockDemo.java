package com.puxinxiaolin.JUCStudy.sggJUC.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReEntryLockDemo {
    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        // 1. 隐式锁
//        reEnrtyM1();

//        ReEntryLockDemo reEntryLockDemo = new ReEntryLockDemo();
//        new Thread(() -> {
//            reEntryLockDemo.m1();
//        }, "t1").start();

        // 2. 显示锁
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t ----come in 外层调用");
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "\t ----内层调用");
                } finally {
//                    lock.unlock();
                }
            } finally {
                lock.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t ----come in 外层调用");
                lock.lock();
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }

    public synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + "\t ----m1 come in");
        m2();
        System.out.println(Thread.currentThread().getName() + "\t ----end");
    }

    public synchronized void m2() {
        System.out.println(Thread.currentThread().getName() + "\t ----m2 come in");
        m3();
    }

    public synchronized void m3() {
        System.out.println(Thread.currentThread().getName() + "\t ----m3 come in");
    }

    private static void reEnrtyM1() {
        final Object object = new Object();

        new Thread(() -> {
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + "\t ----外层调用");
                synchronized (object) {
                    System.out.println(Thread.currentThread().getName() + "\t ----中层调用");
                    synchronized (object) {
                        System.out.println(Thread.currentThread().getName() + "\t ----内层调用");
                    }
                }
            }
        }, "t1").start();
    }
}
