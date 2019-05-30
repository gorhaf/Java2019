package main;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        // 创建线程池
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1));
        // 重新设置任务拒绝策略
        threadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
        // 创建任务
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("任务1");
                try {
                    // 使执行任务的线程睡3秒
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("任务2");
            }
        };
        Runnable runnable3 = new Runnable() {
            @Override
            public void run() {
                System.out.println("任务3");
            }
        };
        // 提交任务
        threadPool.submit(runnable1);
        threadPool.submit(runnable2);
        threadPool.submit(runnable3);
    }
}