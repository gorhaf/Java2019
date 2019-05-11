package main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        // 创建Callable任务列表
        List<Callable<String>> callables = new ArrayList<>();
        // 添加Callable任务
        for (int i = 0; i < 10; i++) {
            callables.add(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    // 模拟任务内容
                    for (int j = 0; j < 199999999; j++) {
                        String x = "j = " + j;
                    }
                    // 将随机数转换为字符串
                    return "当前线程名称是 " + Thread.currentThread().getName() + "，随机数是 " + Math.random();
                }
            });
        }
        // 创建固定大小的线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        try {
            // 提交批量任务
            List<Future<String>> futures = threadPool.invokeAll(callables);
            // 获取任务执行结果
            for (Future<String> future : futures) {
                // 输出任务结果
                System.out.println("任务结果：" + future.get() + "，任务是否完成：" + future.isDone());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        // 关闭线程池
        threadPool.shutdown();
    }
}