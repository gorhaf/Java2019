package main;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        // 创建Callable任务
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() {
                return 1 + 1;
            }
        };
        // 创建固定大小的线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        // 提交任务
        Future<Integer> future = threadPool.submit(callable);
        try {
            // 获取结果
            Object result = future.get();
            // 输出结果
            System.out.println("任务执行完成，结果: " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        // 关闭线程池
        threadPool.shutdown();
    }
}