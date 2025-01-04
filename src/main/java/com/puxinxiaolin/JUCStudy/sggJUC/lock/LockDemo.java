package com.puxinxiaolin.JUCStudy.sggJUC.lock;

import java.util.concurrent.TimeUnit;

class Phone {
    public static synchronized void sendEmail() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("------sendEmail");
    }

    public synchronized void sendSMS() {
        System.out.println("------sendSMS");
    }

    public void hello() {
        System.out.println("------hello");
    }
}

public class LockDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

//        new Thread(phone::sendEmail, "a").start();
        new Thread(() -> {
            phone.sendEmail();
        }, "a").start();

        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        new Thread(phone::sendSMS, "b").start();
//        new Thread(phone::hello, "b").start();
        new Thread(() -> {
            phone2.sendSMS();
        }, "b").start();
    }
}
