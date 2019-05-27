package main;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        // 创建线程池
        ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        // 设置空闲线程保持时间
        threadPool.setKeepAliveTime(1, TimeUnit.SECONDS);
        // 设置核心线程可被销毁
        threadPool.allowCoreThreadTimeOut(true);
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
                    System.out.println("核心线程数：" + corePoolSize + "，最大线程数：" + maximumPoolSize + "，当前线程数：" + poolSize + "，曾经出现过的最大线程数：" + largestPoolSize);
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
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // 当线程池中曾经出现过的最大线程数小于50时一直提交任务
                while (threadPool.getLargestPoolSize() < 10) {
                    // 提交任务
                    threadPool.submit(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                TimeUnit.SECONDS.sleep(3);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    try {
                        // 使当前线程睡100毫秒
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        new Thread(runnable).start();
    }
}