package com.example.myapplication.slice;

import com.example.myapplication.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.app.dispatcher.Group;
import ohos.app.dispatcher.TaskDispatcher;
import ohos.app.dispatcher.task.Revocable;
import ohos.app.dispatcher.task.TaskPriority;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class ThreadManageAbilitySlice extends AbilitySlice {
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(3, 0xD001100, "ThreadManageAbilitySlice");

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_layout_thread_manage);

        /**
         * 1、GlobalTaskDispatcher
         * 全局并发任务分发器，由Ability执行getGlobalTaskDispatcher()获取。适用于任务之间没有联系的情况。
         * 一个应用只有一个GlobalTaskDispatcher，它在程序结束时才被销毁
         */
        TaskDispatcher globalTaskDispatcher = getGlobalTaskDispatcher(TaskPriority.DEFAULT);

        //syncDispatch同步派发任务：派发任务并在当前线程等待任务执行完成。在返回前，当前线程会被阻塞。
        globalTaskDispatcher.syncDispatch(new Runnable() {
            @Override
            public void run() {
                HiLog.info(LABEL_LOG, "sync task1 run");
            }
        });
        HiLog.info(LABEL_LOG, "after sync task1");
        //返回结果sync task1 run -》after sync task1

        //asyncDispatch异步派发任务：派发任务，并立即返回，返回值是一个可用于取消任务的接口
        Revocable revocable = globalTaskDispatcher.asyncDispatch(new Runnable() {
            @Override
            public void run() {
                HiLog.info(LABEL_LOG, "async task1 run");
            }
        });
        HiLog.info(LABEL_LOG, "after async task1");
        //返回结果after async task1-》async task1 run

        //delayDispatch异步延迟派发任务：异步执行，函数立即返回，内部会在延时指定时间后将任务派发到相应队列中。
        // 延时时间参数仅代表在这段时间以后任务分发器会将任务加入到队列中，任务的实际执行时间可能晚于这个时间。
        // 具体比这个数值晚多久，取决于队列及内部线程池的繁忙情况
        final long callTime = System.currentTimeMillis();
        final long delayTime = 50L;
        Revocable revocable2 = globalTaskDispatcher.delayDispatch(new Runnable() {
            @Override
            public void run() {
                HiLog.info(LABEL_LOG, "delayDispatch task1 run");
                final long actualDelay = System.currentTimeMillis() - callTime;
                HiLog.info(LABEL_LOG, "actualDelayTime >= delayTime: %{public}b", (actualDelay >= delayTime));
            }
        }, delayTime);
        HiLog.info(LABEL_LOG, "after delayDispatch task1");
        //返回结果after delayDispatch task1-》delayDispatch task1 run-》actualDelayTime >= delayTime: true


        /**
         * 2、ParallelTaskDispatcher
         * 并发任务分发器，由Ability执行createParallelTaskDispatcher()创建并返回。
         * 与GlobalTaskDispatcher不同的是，ParallelTaskDispatcher不具有全局唯一性，可以创建多个。
         * 开发者在创建或销毁dispatcher时，需要持有对应的对象引用 。
         */
        String dispatcherName2 = "parallelTaskDispatcher";
        TaskDispatcher parallelTaskDispatcher = createParallelTaskDispatcher(dispatcherName2, TaskPriority.DEFAULT);
        //Group任务组：表示一组任务，且该组任务之间有一定的联系，由TaskDispatcher执行createDispatchGroup创建并返回。
        // 将任务加入任务组，返回一个用于取消任务的接口。
        Group group = parallelTaskDispatcher.createDispatchGroup();
        parallelTaskDispatcher.asyncGroupDispatch(group, new Runnable() {
            @Override
            public void run() {
                HiLog.info(LABEL_LOG, "download task1 is running");
            }
        });
        parallelTaskDispatcher.asyncGroupDispatch(group, new Runnable() {
            @Override
            public void run() {
                HiLog.info(LABEL_LOG, "download task2 is running");
            }
        });
        parallelTaskDispatcher.groupDispatchNotify(group, new Runnable() {
            @Override
            public void run() {
                HiLog.info(LABEL_LOG, "the close task is running after all tasks in the group are completed");
            }
        });
        // 可能的执行结果:
        // download task1 is running
        // download task2 is running
        // the close task is running after all tasks in the group are completed

        // 另外一种可能的执行结果：
        // download task2 is running
        // download task1 is running
        // the close task is running after all tasks in the group are completed

        /**
         * 3、SerialTaskDispatcher
         * 串行任务分发器，由Ability执行createSerialTaskDispatcher()创建并返回。
         * 由该分发器分发的所有的任务都是按顺序执行，但是执行这些任务的线程并不是固定的。
         * 如果要执行并行任务，应使用ParallelTaskDispatcher或者GlobalTaskDispatcher，而不是创建多个SerialTaskDispatcher。
         * 如果任务之间没有依赖，应使用GlobalTaskDispatcher来实现。
         * 它的创建和销毁由开发者自己管理，开发者在使用期间需要持有该对象引用。
         */
        String dispatcherName3 = "serialTaskDispatcher";
        TaskDispatcher serialTaskDispatcher = createSerialTaskDispatcher(dispatcherName3, TaskPriority.DEFAULT);

        /**
         * 4、SpecTaskDispatcher
         * 专有任务分发器，绑定到专有线程上的任务分发器。目前已有的专有线程是主线程。
         * UITaskDispatcher和MainTaskDispatcher都属于SpecTaskDispatcher。建议使用UITaskDispatcher。
         */
        TaskDispatcher uiTaskDispatcher = getUITaskDispatcher();
        TaskDispatcher mainTaskDispatcher = getMainTaskDispatcher();

    }
}
