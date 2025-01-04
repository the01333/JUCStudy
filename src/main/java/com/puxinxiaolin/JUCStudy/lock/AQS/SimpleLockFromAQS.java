package com.puxinxiaolin.JUCStudy.lock.AQS;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @Description: 基于AQS实现一个简单的独占锁，1：锁定状态  0：未锁定状态
 * @Author: YccLin
 * @Date: 2024/10/15
 */
public class SimpleLockFromAQS {
    private final Sync sync = new Sync();

    private static class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0, 1)) {
                // 设置当前线程为独占线程
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            } else {
                setExclusiveOwnerThread(null);
                setState(0);
                return true;
            }
        }

        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }
    }

    public void lock() {
        sync.acquire(1);
    }

    public void unlock() {
        sync.release(1);
    }

    public boolean isLocked() {
        return sync.isHeldExclusively();
    }

}
