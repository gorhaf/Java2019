package main;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        // 创建任务
        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    // 使当前线程睡5秒钟
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 获取当前执行任务的线程名称
                System.out.println("线程名称: " + Thread.currentThread().getName());
            }
        };
        // 创建固定大小的线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        // 提交任务
        Future<?> future = threadPool.submit(task);
        try {
            // 获取结果
            Object result = future.get();
            // 输出结果
            System.out.println("任务执行结果: " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        // 关闭线程池
        threadPool.shutdown();
    }
}