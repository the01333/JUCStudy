package com.puxinxiaolin.JUCStudy.demo;

public class ThreadExample {
    public static void main(String[] args) {
        Thread userThread = new Thread(() -> {
            try {
                Thread.sleep(5000);
                System.out.println("User Thread finished");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread daemonThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    System.out.println("Daemon Thread running");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        daemonThread.setDaemon(true);
        userThread.start();
        daemonThread.start();

        System.out.println("Main thread finished");
    }
}
