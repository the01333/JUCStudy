package com.puxinxiaolin.JUCStudy.sggJUC.lockSupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: LockSupport 支持先“唤醒”再正常结束；如果多次调用park，需要多个凭证，而凭证最多只能持有一个
 * @author: YCcLin
 * @date: 2025/1/5
 **/
public class LockSupportDemo3 {
    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t ---- come in");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "\t ---- 被唤醒");
        }, "t1");
        t1.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            LockSupport.unpark(t1);    // 给t1线程发放通行证，让线程不被拦截
            System.out.println(Thread.currentThread().getName() + "\t ---- 发出通知");
        }, "t2").start();
    }
}
