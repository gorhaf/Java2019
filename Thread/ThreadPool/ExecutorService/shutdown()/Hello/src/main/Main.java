package main;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        // 创建Runnable任务
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("执行任务！");
            }
        };
        // 创建固定大小的线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        // 提交任务
        threadPool.submit(runnable);
        // 关闭线程池
        threadPool.shutdown();
    }
}