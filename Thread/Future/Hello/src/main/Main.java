package main;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        // 创建Callable任务
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                // 模拟任务
                for (int i = 0; i < 99999999; i++) {
                    String x = i + "";
                }
                return 1 + 1;
            }
        };
        // 创建线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        // 提交Callable任务
        Future<Integer> future = threadPool.submit(callable);
        // 检查任务状态
        checkTaskState(future);
        // 取消任务
        cancelTask(future);
        try {
            // 获取任务执行结果
            Integer result = future.get();
            // 输出结果
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            // 关闭线程池
            threadPool.shutdown();
        }
    }

    /**
     * 检查任务状态
     *
     * @param future 代表异步任务计算结果
     */
    private static void checkTaskState(Future future) {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("任务是否完成：" + future.isDone() + "，任务是否被取消：" + future.isCancelled());
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

    /**
     * 取消任务
     *
     * @param future 代表异步任务计算结果
     */
    private static void cancelTask(Future future) {
        new Thread() {
            @Override
            public void run() {
                try {
                    // 使当前线程睡1秒钟
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 取消任务
                boolean result = future.cancel(true);
                System.out.println("任务是否取消成功：" + result);
            }
        }.start();
    }
}