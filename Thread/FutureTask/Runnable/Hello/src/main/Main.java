package main;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        // 创建Runnable任务
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // 模拟任务
                for (int i = 0; i < 99999999; i++) {
                    String x = i + "";
                }
                System.out.println("run...");
            }
        };
        // 创建可取消的异步任务
        FutureTask<Integer> futureTask = new FutureTask<>(runnable, 2);
        // 取消任务
        boolean isSuccess = futureTask.cancel(true);
        System.out.println("是否已成功取消任务：" + isSuccess);
        // 提交可取消的异步任务
        new Thread(futureTask).start();
        try {
            // 获取任务执行结果
            Integer result = futureTask.get();
            // 输出任务执行结果
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}