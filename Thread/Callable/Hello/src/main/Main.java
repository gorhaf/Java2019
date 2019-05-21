package main;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        // 创建Callable任务
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 1 + 1;
            }
        };
        // 创建线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        // 提交Callable任务
        Future<Integer> future = threadPool.submit(callable);
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
}