package com.puxinxiaolin.JUCStudy.Thread.synchronizedContent;

public class SynchronizedDemo {
    private int count = 0;

    public synchronized void increment(){
        count++;
    }

    public void incrementWithBlock() {
        synchronized (this) {
            count++;
        }
    }
}
