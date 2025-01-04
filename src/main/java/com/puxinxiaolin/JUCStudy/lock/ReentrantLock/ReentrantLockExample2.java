package com.puxinxiaolin.JUCStudy.lock.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample2 {
    private final ReentrantLock lock = new ReentrantLock();

    private void outerMethod() {
        lock.lock();
        try {
            System.out.println("outerMethod");
            innerMethod();
        } finally {
            lock.unlock();
        }
    }

    private void innerMethod() {
        lock.lock();
        try {
            System.out.println("innerMethod");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockExample2 example = new ReentrantLockExample2();
        example.outerMethod();
    }
}
