package com.puxinxiaolin.JUCStudy.heimaJUC.basicThread.example;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: Thread
 * @Author: YccLin
 * @Date: 2024/10/25
 */
@Slf4j(topic = "ThreadExample1")
public class ThreadExample1 {
    public static void main(String[] args) {
//        Thread t1 = new Thread(){
//            @Override
//            public void run() {
//                log.debug("running");
//            }
//        };
//        t1.setName("t1");
//        t1.start();

        Thread t2 = new Thread(() -> log.debug("running"));
        t2.setName("t2");
        t2.start();

        log.debug("running");
    }
}
