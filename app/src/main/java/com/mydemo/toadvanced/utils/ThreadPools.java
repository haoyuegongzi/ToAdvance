package com.mydemo.toadvanced.utils;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.Executors.*;

/**
 * 作者：Created by chen1 on 2019/10/30.
 * 邮箱：chen126jie@163.com
 * TODO：创建线程池
 */
public class ThreadPools {

    /**
     * 创建一个可缓存的线程池,会对线程池大小做限制,线程池大小完全依赖于JVM
     * 如果线程池的大小超过了处理任务所需要的线程，那么就会回收部分空闲（60秒不执行任务）的线程，
     * 当任务数增加时，此线程池又可以智能的添加新线程来处理任务。
     * @param runnable
     */
    public static ExecutorService getCachedThreadPool(Runnable runnable){
        ExecutorService service = newCachedThreadPool();
        service.execute(runnable);
        return service;
    }

    /**
     * 创建一个单线程的线程池
     * @param runnable
     */
    public static ExecutorService getSingleThreadPool(Runnable runnable){
        ExecutorService service = newSingleThreadExecutor();
        service.execute(runnable);
        service.isTerminated();
        return service;
    }

    static ExecutorService newFixedThreadPoolService = null;

    /**
     * 创建一个固定长度的线程池,控制线程最大并发数，超出的线程会在队列中等待。
     * @param nThreads:线程池长度
     * @param runnable：线程
     */
    public synchronized static ExecutorService getNewFixedThreadPool(int nThreads, Runnable runnable){
        if (null == newFixedThreadPoolService){
            newFixedThreadPoolService = newFixedThreadPool(nThreads);
        }
        newFixedThreadPoolService.submit(runnable);
        return newFixedThreadPoolService;
    }

    static ExecutorService newScheduledThreadPoolService = null;

    /**
     * 创建一个定长线程池,支持定时及周期性任务执行。
     * @param nThreads:线程池可缓存线程的数量
     * @param runnable：线程
     */
    public synchronized static ExecutorService  getScheduledThreadPool(int nThreads, Runnable runnable){
        if (newScheduledThreadPoolService == null){
            newScheduledThreadPoolService = newScheduledThreadPool(nThreads);
        }
        newScheduledThreadPoolService.submit(runnable);
        return newScheduledThreadPoolService;
    }

    static ScheduledExecutorService mScheduledExecutorServiceFixedRate;
    /**同getScheduledThreadPoolExecutor()方法*/
    public synchronized static ScheduledExecutorService getScheduledExecutorServiceFixedRate(
                    int nThreads, Runnable runnable, long initialDelay, long period, TimeUnit unit){
        if (mScheduledExecutorServiceFixedRate == null){
            mScheduledExecutorServiceFixedRate = newScheduledThreadPool(nThreads);
        }
        mScheduledExecutorServiceFixedRate.scheduleAtFixedRate(runnable, initialDelay, period, unit);
        return mScheduledExecutorServiceFixedRate;
    }

    static ScheduledExecutorService mScheduledExecutorServiceFixedDelay;
    /**同getScheduledThreadPoolExecutorWithFixedDelay()方法*/
    public synchronized static ScheduledExecutorService getScheduledExecutorServiceFixedDelay(
                        int nThreads, Runnable runnable, long initialDelay, long period, TimeUnit unit){
        if (mScheduledExecutorServiceFixedDelay == null){
            mScheduledExecutorServiceFixedDelay = newScheduledThreadPool(nThreads);
        }
        mScheduledExecutorServiceFixedDelay.scheduleWithFixedDelay(runnable, initialDelay, period, unit);
        return mScheduledExecutorServiceFixedDelay;
    }

    static ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = null;
    /**
     * 创建一个可缓存并且可以周期性执行任务的线程池，优先保证任务执行的频率。
     * 该方法在initialDelay时长后第一次执行任务，以后每隔period时长，再次执行任务。
     * 如果上一个任务执行完毕，则当前任务立即执行，如果上一个任务没有执行完毕，则需要等上一个任务执行完毕后立即执行。
     * @param nThreads:线程池可缓存线程的数量
     * @param runnable:线程
     * @param initialDelay：延迟执行的时间
     * @param period：每次任务执行的时间间隔
     * @param unit：时间单位——S,ms,min,h......
     */
    public synchronized static ScheduledThreadPoolExecutor getScheduledThreadPoolExecutor(int nThreads, Runnable runnable,
                                                                   long initialDelay, long period,
                                                                   TimeUnit unit){
        if (null == scheduledThreadPoolExecutor){
            scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(nThreads);
        }
        // scheduleAtFixedRate：该方法在initialDelay时长后第一次执行任务，以后每隔period时长，再次执行任务。
        // 注意，period是从任务开始执行算起的。开始执行任务后，定时器每隔period时长检查该任务是否完成，
        // 如果完成则再次启动任务，否则等该任务结束后才再次启动任务。
        scheduledThreadPoolExecutor.scheduleAtFixedRate(runnable, initialDelay, period, unit);
        return scheduledThreadPoolExecutor;
    }

    static ScheduledThreadPoolExecutor scheduleWithFixedDelay;

    /**
     * 创建一个可缓存并且可以周期性执行任务的线程池，优先保证任务执行的间隔。
     * 任务在在initialDelay时长后第一次执行任务，以后每当任务执行完成后，等待delay时长，再次执行任务。
     * 如果任务执行过程中抛出了异常，那么过ScheduledExecutorService就会停止执行任务，且也不会再周期地执行该任务了。
     * 所以你如果想保住任务都一直被周期执行，那么catch一切可能的异常。
     * @param nThreads:线程池可缓存线程的数量
     * @param runnable:线程
     * @param initialDelay：延迟执行的时间
     * @param period：每次任务执行的时间间隔
     * @param unit：时间单位——S,ms,min,h......
     */
    public synchronized static ScheduledThreadPoolExecutor getScheduledThreadPoolExecutorWithFixedDelay(int nThreads, Runnable runnable,
                                                                                 long initialDelay, long period,
                                                                                 TimeUnit unit){
        if (null == scheduleWithFixedDelay){
            scheduleWithFixedDelay = new ScheduledThreadPoolExecutor(nThreads);
        }
        // 在initialDelay时长后第一次执行任务，以后每当任务执行完成后，等待delay时长，再次执行任务。
        scheduleWithFixedDelay.scheduleWithFixedDelay(runnable, initialDelay, period, unit);
        return scheduleWithFixedDelay;
    }

    static ScheduledThreadPoolExecutor scheduleService = null;
    /**
     * 创建一个可缓存并且可以周期性执行任务的线程池，任务在延迟delay时长后执行，只执行一次。
     * @param nThreads:线程池可缓存线程的数量
     * @param runnable:线程
     * @param delay：延迟执行的时间
     * @param unit：时间单位——S,ms,min,h......
     */
    public synchronized static ScheduledThreadPoolExecutor getScheduledThreadPoolExecutor(int nThreads, Runnable runnable,
                                                                   long delay, TimeUnit unit){
        if (null == scheduleService){
            scheduleService = new ScheduledThreadPoolExecutor(nThreads);
        }
        // schedule方法调度的任务在delay时长的延迟后只执行一次。
        scheduleService.schedule(runnable, delay, unit);
        return scheduleService;
    }

    static ScheduledThreadPoolExecutor scheduleServiceExecute = null;

    /**
     * 创建一个可缓存的线程池
     * @param nThreads:线程池可缓存线程的数量
     * @param runnable:线程
     */
    public synchronized static ScheduledThreadPoolExecutor getScheduledThreadPoolExecutorExecute(int nThreads, Runnable runnable){
        if (null == scheduleServiceExecute){
            scheduleServiceExecute = new ScheduledThreadPoolExecutor(nThreads);
        }
        // execute方法调度的任务在delay时长的延迟后只执行一次。
        scheduleServiceExecute.execute(runnable);
        return scheduleServiceExecute;
    }
}
