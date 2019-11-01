package com.mydemo.toadvanced.utils;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 作者：Created by chen1 on 2019/10/30.
 * 邮箱：chen126jie@163.com
 * TODO：创建线程池
 */
public class ThreadPools {

    /**
     *
     * @param runnable
     */
    public static void getCachedThreadPool(Runnable runnable){
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(runnable);
        service.shutdown();
    }

    /**
     * 创建一个单线程的线程池
     * @param runnable
     */
    public static void getSingleThreadPool(Runnable runnable){
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(runnable);
    }
}
