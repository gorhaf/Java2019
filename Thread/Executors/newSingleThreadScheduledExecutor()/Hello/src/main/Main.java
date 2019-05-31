package main;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        // 创建执行定时任务的线程池
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        // 创建任务
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("你好，延时任务！");
            }
        };
        // 提交延时任务
        scheduledThreadPool.schedule(runnable, 3, TimeUnit.SECONDS);
        // 关闭线程池
        scheduledThreadPool.shutdown();
    }
}