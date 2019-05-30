package main;

import java.util.concurrent.*;

public class Main {

    /**
     * 自定义任务拒绝策略
     */
    private static class SimplePolicy implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("我是自定义任务拒绝策略");
        }
    }

    public static void main(String[] args) {
        // 创建线程池
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1));
        // 重新设置任务拒绝策略
        threadPool.setRejectedExecutionHandler(new SimplePolicy());
        // 创建任务
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    // 使执行任务的线程睡Integer.MAX_VALUE秒
                    TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        // 提交任务
        threadPool.submit(runnable);
        threadPool.submit(runnable);
        threadPool.submit(runnable);
    }
}