package com.ruoyi.common.utils.customer;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CustomerShuffleThreadPoolUtil {
    private static volatile ThreadPoolExecutor executor;

    private CustomerShuffleThreadPoolUtil() {}

    public static ThreadPoolExecutor getThreadPool() {
        if(executor == null) {
            synchronized (CustomerShuffleThreadPoolUtil.class) {
                if(executor == null) {
                    executor = new ThreadPoolExecutor(16, 20, 60, TimeUnit.SECONDS,
                            new LinkedBlockingQueue<Runnable>(1024));
                }
            }
        }
        return executor;
    }
}
