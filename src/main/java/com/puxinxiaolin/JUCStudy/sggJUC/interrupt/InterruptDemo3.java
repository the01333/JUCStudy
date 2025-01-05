package com.puxinxiaolin.JUCStudy.sggJUC.interrupt;

import java.util.concurrent.TimeUnit;

public class InterruptDemo3 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() +
                            "的中断标志位: " + Thread.currentThread().isInterrupted() + " 程序停止");
                    break;
                }

                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();   // 遇到sleep、wait、join等，会清除中断标志并抛异常，我们需要在catch异常时再 interrupt 一次
                    e.printStackTrace();
                }

                System.out.println("-----hello isInterrupted3");
            }
        }, "t1");
        t1.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(t1::interrupt, "t2").start();
    }
}
