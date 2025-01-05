package com.puxinxiaolin.JUCStudy.sggJUC.interrupt;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

public class InterruptDemo2 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 300; i++) {
                System.out.println("-----:" + i);
            }
            System.out.println("t1线程调用interrupt()后的中断标志位02: " + Thread.currentThread().isInterrupted());
        }, "t1");
        t1.start();

        System.out.println("t1的默认中断标志位: " + t1.isInterrupted());

        try {
            TimeUnit.MILLISECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t1.interrupt();    // 打断只是会把中断标记设置为true，但并不会停止线程，需要自己处理
        System.out.println("t1线程调用interrupt()后的中断标志位01: " + t1.isInterrupted());

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("t1线程调用interrupt()后的中断标志位03: " + t1.isInterrupted());
    }
}
