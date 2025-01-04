package com.puxinxiaolin.JUCStudy.lock.ReentrantLock;

public class ReentrantLockExample {
    public synchronized void methodA() {
        System.out.println("Method A start");
        methodB();  // 调用另一个同步方法
        System.out.println("Method A end");
    }

    public synchronized void methodB() {
        System.out.println("Method B start");
        // 其他操作
        System.out.println("Method B end");
    }

    public static void main(String[] args) {
        ReentrantLockExample example = new ReentrantLockExample();
        new Thread(example::methodA).start();
    }

}
