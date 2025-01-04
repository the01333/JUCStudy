package com.puxinxiaolin.JUCStudy.Thread.rejectPolicy;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description: 自定义拒绝策略
 * @Author: YccLin
 * @Date: 2024/10/14
 */
public class CustomRejectPolicy implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        // 自定义的拒绝处理逻辑
        System.out.println("Task " + r.toString() + "rejected");
    }
}
