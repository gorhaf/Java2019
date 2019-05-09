package main;

import java.util.concurrent.*;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        // 创建Runnable任务
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    // 使当前线程睡3秒钟
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("执行任务的线程名称: " + Thread.currentThread().getName());
            }
        };
        // 创建固定大小的线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        // 检查线程池状态
        checkThreadPoolState(threadPool);
        // 提交任务
        threadPool.submit(runnable);
        // 关闭线程池
        threadPool.shutdown();
    }

    /**
     * 检查线程池状态
     *
     * @param threadPool 线程池
     */
    private static void checkThreadPoolState(ExecutorService threadPool) {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("检查线程池是否已关闭: " + threadPool.isShutdown());
                    System.out.println("检查线程池是否已关闭和任务是否都已完成: " + threadPool.isTerminated());
                    try {
                        // 使当前线程睡1秒钟
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}