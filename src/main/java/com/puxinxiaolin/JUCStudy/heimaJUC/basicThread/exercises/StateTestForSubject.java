package com.puxinxiaolin.JUCStudy.heimaJUC.basicThread.exercises;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "StateTestForSubject")
public class StateTestForSubject {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            log.debug("洗水壶");
            Sleeper.sleep(1);
            log.debug("烧开水");
            Sleeper.sleep(5);
        }, "水壶准备-t1");

        Thread t2 = new Thread(() -> {
            log.debug("洗茶壶");
            Sleeper.sleep(1);
            log.debug("洗茶杯");
            Sleeper.sleep(2);
            log.debug("洗茶叶");
            Sleeper.sleep(1);

            // 等待t1结束
            try {
                t1.join();
                log.debug("水壶已经烧好了，可以泡茶了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "工具准备-t2");

        t1.start();
        t2.start();
    }
}
