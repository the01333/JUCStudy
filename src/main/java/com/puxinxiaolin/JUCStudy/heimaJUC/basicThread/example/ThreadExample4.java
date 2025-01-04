package com.puxinxiaolin.JUCStudy.heimaJUC.basicThread.example;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j(topic = "ThreadExample4")
public class ThreadExample4 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t.start();
        log.info("t state:{}", t.getState());

        Thread.sleep(500);
        log.info("t state:{}", t.getState());
    }
}
