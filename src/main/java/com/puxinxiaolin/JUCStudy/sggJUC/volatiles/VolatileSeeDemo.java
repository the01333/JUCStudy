package com.puxinxiaolin.JUCStudy.sggJUC.volatiles;

import java.util.concurrent.TimeUnit;

public class VolatileSeeDemo {
    // volatile 保证在多线程下的可见性
    static volatile boolean flag = true;

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t ----come in");
            while (flag) {

            }
            System.out.println(Thread.currentThread().getName() + "\t ----flag被设置成false，程序停止");
        }, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        flag = false;
        System.out.println(Thread.currentThread().getName() + "\t ----修改完成，flag为" + flag);
    }
}
