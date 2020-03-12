package com.yoyozhangh.github.task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 线程池
 * 线程池顾名思义 就是对线程进行管理，控制线程的数量及执行策略，从而减少性能消耗，使项目更好的稳定运行
 * <p>
 * <p>
 * corePoolSize 核心线程数
 * maximumPoolSize 最大线程数
 * keepAliveTime 多余线程存活时间
 * TimeUnit 存活的单位
 * BlockingQueue 任务队列
 * ThreadFactory 线程工厂
 * RejectedExecutionHandler 拒绝任务
 * <p>
 * <p>
 * 队列
 * 队列：是一种特殊的线性结构，允许在线性结构的前端进行删除/读取操作，允许在线性结构的后端进行插入操作
 * 这种线性结构具有 先进先出的操作特点
 * 但是在实际应用中，队列中的元素有可能不是以进入的顺序为排序依据的，
 * <p>
 * 拒绝任务 RejectedExecutionHandler
 * 当提交给线程池的某一个新任务无法直接被线程池中核心线程直接处理，又无法加入等待队列，也无法创建新的线程执行
 * 又或线程池已经调用shutdown 方法停止了工作，又或者线程池不是处于正常的工作状态，这时候ThreadPoolExecutor
 * 线程池会拒绝处理这个任务，触发创建ThreadPoolExecutor线程池时定义的RejectedExecutionHandler 接口的实现
 * <p>
 * <p>
 * Executors
 * <p>
 * CacheThreadPool 弹性缓存线程池 特点：用newCachedThreadPool() 方法创建该线程池对象，创建之初里面一个
 * 线程都没有，当 execute 方法或者submit方法向线程池提交任务时，会自动新建线程；如果线程池中有空余线程，
 * 则不会新建；这种线程池一般最多情况可以容纳几万个线程，里面的线程空余60s会被回收
 * <p>
 * SingleThreadPool 单线程线程池，特点：池中只有一个线程，如果扔5个任务进来，那么有4个任务将排队，作用是
 * 保证任务的顺序执行
 * <p>
 * FixedThreadPool 特点固定池中的线程的个数
 * <p>
 * ScheduledThreadpool 定时器线程池
 * <p>
 * ForkJoinPool JDK1.7 引入，根据CPU把任务分成小模块执行，执行后合并
 */
public class ProrityThreadPoolExecutor extends ThreadPoolExecutor {


    public ProrityThreadPoolExecutor(int corePoolSize,
                                     int maximumPoolSize,
                                     long keepAliveTime,
                                     TimeUnit unit,
                                     BlockingQueue<Runnable> workQueue,
                                     ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }


    public void submitTask(AsyncTaskInstance taskInstance) {
        execute(taskInstance);
    }
}
