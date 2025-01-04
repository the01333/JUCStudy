package com.puxinxiaolin.JUCStudy.heimaJUC.basicThread.example;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: Two Phrase Termination
 * @Author: YccLin
 * @Date: 2024/10/25
 */
public class ThreadExample5 {
    public static void main(String[] args) throws InterruptedException {
        TwoPhraseTermination twoPhraseTermination = new TwoPhraseTermination();
        twoPhraseTermination.start();

        Thread.sleep(3500);
        twoPhraseTermination.stop();
    }
}

@Slf4j(topic = "TwoPhraseTermination")
class TwoPhraseTermination {
    private Thread monitor;

    public void start() {
        monitor = new Thread(() -> {
            while (true) {
                Thread currentThread = Thread.currentThread();
                if (currentThread.isInterrupted()) {
                    log.debug("料理后事");
                    break;
                }
                try {
                    Thread.sleep(1000);   // sleep被打断，currentThread.isInterrupted() 的值是 false
                    log.debug("执行监控记录");     // 正常情况被打断，currentThread.isInterrupted() 的值是 true
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    currentThread.interrupt();   // 重新设置打断标记
                }
            }
        });
        monitor.start();
    }

    public void stop() {
        monitor.interrupt();
    }
}
