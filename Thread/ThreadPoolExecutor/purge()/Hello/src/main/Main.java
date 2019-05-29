package main;

import java.util.Iterator;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        // 创建线程池
        ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        // 获取线程池信息
        getThreadPoolInfo(threadPool);
        // 向线程池提交任务
        submitTask(threadPool);
        try {
            // 使主线程睡3秒
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 获取任务队列
        BlockingQueue<Runnable> workQueue = threadPool.getQueue();
        // 获取任务队列迭代器
        Iterator<Runnable> iterator = workQueue.iterator();
        // 迭代任务队列中的元素
        while (iterator.hasNext()) {
            // 获取任务队列中的任务对象并将其转换为RunnableFuture类型
            RunnableFuture task = (RunnableFuture) iterator.next();
            // 取消任务
            boolean cancelled = task.cancel(true);
            System.out.println("任务是否取消成功：" + cancelled);
        }
        // 清除已取消的任务
        threadPool.purge();
    }

    /**
     * 获取线程池信息
     *
     * @param threadPool 线程池
     */
    private static void getThreadPoolInfo(ThreadPoolExecutor threadPool) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                BlockingQueue<Runnable> workQueue = threadPool.getQueue();
                while (true) {
                    // 获取正在执行任务的线程数
                    int activeCount = threadPool.getActiveCount();
                    // 获取已经安排执行的任务数
                    long taskCount = threadPool.getTaskCount();
                    // 获取已完成执行的任务数
                    long completedTaskCount = threadPool.getCompletedTaskCount();
                    // 获取任务队列大小
                    int workQueueSize = workQueue.size();
                    // 输出信息
                    System.out.println("正在执行任务的线程数：" + activeCount
                            + "，已经安排执行的任务数：" + taskCount
                            + "，已完成执行的任务数：" + completedTaskCount
                            + "，任务队列大小：" + workQueueSize);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        new Thread(runnable).start();
    }

    /**
     * 提交任务
     *
     * @param threadPool 线程池
     */
    private static void submitTask(ThreadPoolExecutor threadPool) {
        for (int i = 0; i < 5; i++) {
            // 创建任务
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            // 提交任务
            threadPool.submit(runnable);
        }
    }
}