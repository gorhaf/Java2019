package main;

import java.util.List;
import java.util.concurrent.*;

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
        // 提交任务
        threadPool.submit(runnable);
        // 关闭线程池并返回线程池中未执行的任务
        List<Runnable> runnables = threadPool.shutdownNow();
        // 输出未执行的任务的个数
        System.out.println("未执行的任务数量: " + runnables.size());
    }
}