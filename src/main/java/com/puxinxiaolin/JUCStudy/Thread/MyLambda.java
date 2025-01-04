package com.puxinxiaolin.JUCStudy.Thread;

public class MyLambda {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> System.out.println("Lambda Thread is running"));
        thread.start();
    }
}
