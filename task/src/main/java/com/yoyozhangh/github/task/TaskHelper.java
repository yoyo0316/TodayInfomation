package com.yoyozhangh.github.task;

public class TaskHelper {

    public static void submitTask(ITaskBackground iTaskBackground,ITaskCallback iTaskCallback){
        AsyncTaskInstance instance = AsyncTaskInstance.getInstance(iTaskBackground, iTaskCallback);

        //TODO 构建线程池管理器
        TaskScheduler.getInstance().submit(instance);
    }


}
