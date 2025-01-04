package com.puxinxiaolin.JUCStudy.lock.OptimisticLock.ABA;

/**
 * @Description: 引入版本号
 * @Author: YccLin
 * @Date: 2024/10/15
 */
public class ABAVersionExample {
    private ABAVersion abaVersion = new ABAVersion(0, 0);

    static class ABAVersion {
        int value;
        int version;

        ABAVersion(int value, int version) {
            this.value = value;
            this.version = version;
        }
    }

    public boolean compareAndSwap(ABAVersion current, int newValue) {
        synchronized (this) {
            if (current.version == abaVersion.version && current.value == newValue) {
                abaVersion.value = newValue;
                abaVersion.version++;
                return true;
            }
            return false;
        }
    }
}
