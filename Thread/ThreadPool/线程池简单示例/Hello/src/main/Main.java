package main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        // 创建任务
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int x = 1 + 1;
                System.out.println("1+1=" + x);
            }
        };
        // 创建线程池
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        // 提交任务
        fixedThreadPool.submit(runnable);
        //
        fixedThreadPool.execute(runnable);
        // 关闭线程池
        fixedThreadPool.shutdown();
    }
}