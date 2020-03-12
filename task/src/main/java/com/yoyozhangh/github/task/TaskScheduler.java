package com.yoyozhangh.github.task;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import com.yoyozhangh.github.task.tools.ThreadUtil;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class TaskScheduler {

    private static TaskScheduler instance;
    private final Handler handler;
    private final ProrityThreadPoolExecutor executor;

    private int corePoolSize = ThreadUtil.CPU_NUM + 1;
    private int maximumPoolSize = ThreadUtil.CPU_NUM * 3;
    private int keepAliveTime = 1;


    interface ITaskSchedulerType {
        int SUBMIT_TASK = 0;

    }

    private TaskScheduler() {
        //用于消息调度的线程
        HandlerThread handlerThread = new HandlerThread("task-thread");
        handlerThread.start();
        this.handler = new Handler(handlerThread.getLooper(), new Handler.Callback() {

            // HandlerThread handleMessage 运行在子线程
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what) {
                    case ITaskSchedulerType.SUBMIT_TASK:
                        doSubmitTask((AsyncTaskInstance) msg.obj);
                        break;
                }

                return false;
            }
        });

        // 创建一个线程池
        BlockingQueue<Runnable> poolQueue = new LinkedBlockingDeque<>();//无大小限制的队列

        this.executor = new ProrityThreadPoolExecutor(corePoolSize,
                maximumPoolSize,keepAliveTime, TimeUnit.MINUTES,poolQueue,
                new TaskThreadFactory()
                );
    }

    private void doSubmitTask(AsyncTaskInstance taskInstance) {
        executor.submitTask(taskInstance);
    }


    public static TaskScheduler getInstance() {
        if (instance == null) {
            instance = new TaskScheduler();
        }
        return instance;
    }


    public void submit(AsyncTaskInstance instance) {

        handler.sendMessage(handler.obtainMessage(ITaskSchedulerType.SUBMIT_TASK,instance));
    }
}
