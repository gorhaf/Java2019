package main;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        // 创建线程池
        ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        // 获取线程池信息
        getThreadPoolInfo(threadPool);
        // 模拟提交任务
        submitTask(threadPool);
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
                // 获取任务队列
                BlockingQueue<Runnable> workQueue = threadPool.getQueue();
                // 每隔1秒钟输出一次线程池中的各种线程数
                while (true) {
                    // 获取线程池中核心线程数
                    int corePoolSize = threadPool.getCorePoolSize();
                    // 获取线程池中最大线程数
                    int maximumPoolSize = threadPool.getMaximumPoolSize();
                    // 获取线程池中当前线程数
                    int poolSize = threadPool.getPoolSize();
                    // 获取线程池中曾经出现过的最大线程数
                    int largestPoolSize = threadPool.getLargestPoolSize();
                    // 获取正在执行任务的线程数
                    int activeCount = threadPool.getActiveCount();
                    // 获取已经安排执行的任务数。因为任务和线程的状态可能在计算期间动态改变，所以返回的值仅是近似值。
                    long taskCount = threadPool.getTaskCount();
                    // 获取已完成执行的任务数。因为任务和线程的状态可能在计算期间动态改变，所以返回的值仅是近似值。
                    long completedTaskCount = threadPool.getCompletedTaskCount();
                    // 获取任务队列大小
                    int workQueueSize = workQueue.size();
                    // 输出各项结果
                    System.out.println("核心线程数：" + corePoolSize
                            + "，最大线程数：" + maximumPoolSize
                            + "，当前线程数：" + poolSize
                            + "，曾经出现过的最大线程数：" + largestPoolSize
                            + "，正在执行任务的线程数：" + activeCount
                            + "，已经安排执行的任务数：" + taskCount
                            + "，已完成执行的任务数：" + completedTaskCount
                            + "，任务队列大小：" + workQueueSize);
                    try {
                        // 使当前线程睡1秒钟
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
     * 模拟提交任务
     *
     * @param threadPool 线程池
     */
    private static void submitTask(ThreadPoolExecutor threadPool) {
        for (int i = 0; i < 10; i++) {
            int sleepTime = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(sleepTime + 1);
                        // TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            threadPool.submit(runnable);
        }
    }
}