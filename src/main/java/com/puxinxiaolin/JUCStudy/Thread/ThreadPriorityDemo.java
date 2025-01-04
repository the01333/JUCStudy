package com.puxinxiaolin.JUCStudy.Thread;

public class ThreadPriorityDemo {
    public static void main(String[] args) {
        Thread lowThread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Low priority thread running");
            }
        });

        Thread FastThread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Fast priority thread running");
            }
        });

        lowThread.setPriority(Thread.MIN_PRIORITY);
        FastThread.setPriority(8);

        lowThread.start();
        FastThread.start();
    }
}
