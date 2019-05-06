package main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        // 创建任务
        Runnable r = new Runnable() {
            @Override
            public void run() {
                // 获取当前执行任务的线程名称
                System.out.println("线程名称: " + Thread.currentThread().getName());
            }
        };
        // 创建固定大小的线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        // 循环提交任务
        for (int i = 0; i < 10; i++) {
            threadPool.execute(r);
        }
        // 关闭线程池
        threadPool.shutdown();
    }
}