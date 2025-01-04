package com.puxinxiaolin.JUCStudy.heimaJUC.basicThread.example;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: Runnable
 * @Author: YccLin
 * @Date: 2024/10/25
 */
@Slf4j(topic = "ThreadExample2")
public class ThreadExample2 {
    public static void main(String[] args) {
        Runnable r = () -> log.info("running");

        Thread t = new Thread(r);
        t.start();
    }
}
