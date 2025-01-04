package com.puxinxiaolin.JUCStudy.lock.OptimisticLock.ABA;

import java.util.concurrent.atomic.AtomicStampedReference;

public class ABAExample2 {
    private static AtomicStampedReference<Integer> atomicStampedRef =
            new AtomicStampedReference<>(100, 0);

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            int[] stampHolder = new int[1];
            Integer value = atomicStampedRef.get(stampHolder);
            System.out.println("Thread t1 initial value: " + value + ", stamp: " + stampHolder[0]);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 在休眠期间，版本号被t2修改。导致这时候读取到的版本号是0，而预期的是1，但是在休眠期间已经被修改成2了
            boolean success = atomicStampedRef.compareAndSet(value, value + 1,
                    stampHolder[0], stampHolder[0] + 1);
            System.out.println("Thread t1 update success: " + success);
        });

        Thread t2 = new Thread(() -> {
            int[] stampHolder = new int[1];
            Integer value = atomicStampedRef.get(stampHolder);
            System.out.println("Thread t2 initial value: " + value + ", stamp: " + stampHolder[0]);
            atomicStampedRef.compareAndSet(value, value + 1, stampHolder[0], stampHolder[0] + 1);
            atomicStampedRef.compareAndSet(value + 1, value, stampHolder[0] + 1, stampHolder[0] + 2);
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int[] stampHolder = new int[1];
        System.out.println("Final value: " + atomicStampedRef.get(stampHolder) + ", final stamp: " + stampHolder[0]);
    }
}
