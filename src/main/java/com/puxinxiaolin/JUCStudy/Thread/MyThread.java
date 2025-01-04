package com.puxinxiaolin.JUCStudy.Thread;

/**
 * @Description: 继承Thread，重写run
 * @Author: YccLin
 * @Date: 2024/10/14
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("MyThread is running");
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.setPriority(NORM_PRIORITY);
        myThread.start();
    }
}
