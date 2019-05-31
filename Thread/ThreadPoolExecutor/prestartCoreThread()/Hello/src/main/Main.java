package main;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        // 创建线程池
        ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        // 获取线程池信息
        getThreadPoolInfo(threadPool);
        // 预先创建好单个核心线程
        boolean prestartCoreThread = threadPool.prestartCoreThread();
        System.out.println("预先创建核心线程是否成功：" + prestartCoreThread);
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
                    // 输出各项结果
                    System.out.println("核心线程数：" + corePoolSize
                            + "，最大线程数：" + maximumPoolSize
                            + "，当前线程数：" + poolSize
                            + "，曾经出现过的最大线程数：" + largestPoolSize);
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
}