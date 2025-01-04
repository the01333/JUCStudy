package com.puxinxiaolin.JUCStudy.Thread.threadLocal;

/**
 * @Description: 模拟web应用的用户上下文util
 * @Author: YccLin
 * @Date: 2024/10/15
 */
public class UserContext {
    private static final ThreadLocal<String> userThreadLocal = ThreadLocal.withInitial(() -> null);

    public static void setCurrentUser(String user) {
        userThreadLocal.set(user);
    }

    public static String getCurrentUser() {
        return userThreadLocal.get();
    }

    public static void clear(String user) {
        userThreadLocal.remove();
    }
}
