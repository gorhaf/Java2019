package main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Main {

    /**
     * 自定义线程工厂
     */
    private static class CustomThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            // 创建线程
            Thread thread = new Thread(r);
            // 设置线程名称
            thread.setName("Custom");
            // 设置线程优先级
            thread.setPriority(10);
            // 设置线程是否为后台线程
            thread.setDaemon(false);
            // 返回线程
            return thread;
        }
    }

    public static void main(String[] args) {
        // 创建任务
        Runnable r = new Runnable() {
            @Override
            public void run() {
                // 获取当前执行任务的线程名称
                System.out.println("线程名称: " + Thread.currentThread().getName());
            }
        };
        // 创建固定大小的线程池并指定线程工厂
        ExecutorService threadPool = Executors.newFixedThreadPool(5, new CustomThreadFactory());
        // 循环提交任务
        for (int i = 0; i < 10; i++) {
            threadPool.submit(r);
        }
        // 关闭线程池
        threadPool.shutdown();
    }
}