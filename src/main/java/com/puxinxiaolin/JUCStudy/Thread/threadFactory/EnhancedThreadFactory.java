package com.puxinxiaolin.JUCStudy.Thread.threadFactory;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Description: 扩展了日志记录
 * @Author: YccLin
 * @Date: 2024/10/14
 */
public class EnhancedThreadFactory extends CustomThreadFactory {
    private static final Logger logger = Logger.getLogger(EnhancedThreadFactory.class.getName());

    public EnhancedThreadFactory(String namePrefix, Boolean daemon, int priority) {
        super(namePrefix, daemon, priority);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = super.newThread(r);
        logger.log(Level.INFO, "Create new thread:{0}", thread.getName());
        return thread;
    }
}
