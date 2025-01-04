synchronized使用于方法上，锁对象为当前对象this。 == 对方法内的某段逻辑加synchronized，锁对象为当前对象this。

public synchronized void increment() {
    counter++;
}
上面等价于下面
public static void increment() {
        synchronized(this) {
    }
}
