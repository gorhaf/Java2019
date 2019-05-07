package main;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        // 创建任务
        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    // 模拟需要3秒击退敌军
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("击退敌军");
            }
        };
        // 创建固定大小的线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        // 提交任务
        Future<String> future = threadPool.submit(task, "速速就地驻扎");
        try {
            // 获取结果
            Object result = future.get();
            // 输出结果
            System.out.println("任务执行完成，打开锦囊: " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        // 关闭线程池
        threadPool.shutdown();
    }
}