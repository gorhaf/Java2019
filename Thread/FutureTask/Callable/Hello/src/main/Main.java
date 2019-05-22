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
        // 创建可取消的异步任务
        FutureTask<Integer> futureTask = new FutureTask<>(callable);
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