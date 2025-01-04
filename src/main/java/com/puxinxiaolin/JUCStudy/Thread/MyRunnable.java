package com.puxinxiaolin.JUCStudy.Thread;

/**
 * @Description: 启动线程需要包裹在Thread里
 * @Author: YccLin
 * @Date: 2024/10/14
 */
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("MyRunnable is running");
    }

    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();
    }
}
