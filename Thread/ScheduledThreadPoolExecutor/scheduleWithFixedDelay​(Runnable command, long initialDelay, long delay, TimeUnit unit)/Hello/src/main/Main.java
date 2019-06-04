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
                try {
                    // 使当前执行任务的线程睡1秒
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("你好，周期性任务！");
            }
        };
        // 提交延时任务
        scheduledThreadPool.scheduleWithFixedDelay(runnable, 3, 1, TimeUnit.SECONDS);
        try {
            // 使主线程睡10秒钟
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 关闭线程池
        scheduledThreadPool.shutdown();
    }
}