package com.puxinxiaolin.JUCStudy.lock.CAS;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public void increment() {
        int oldVal, newVal;
        do {
            oldVal = atomicInteger.get();
            newVal = oldVal + 1;
        } while (!atomicInteger.compareAndSet(oldVal, newVal));
    }

    public int getVal() {
        return atomicInteger.get();
    }

    public static void main(String[] args) {
        CASDemo example = new CASDemo();
        example.increment();
        System.out.println("Value: " + example.getVal());
    }
}
