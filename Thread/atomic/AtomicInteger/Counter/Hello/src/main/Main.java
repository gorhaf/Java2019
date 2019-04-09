package main;

import lab.Counter;

public class Main {

    /**
     * 主入口
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {
        // 计算任务
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    // 使当前线程睡100毫秒
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 递增counter值
                Counter.increment();
            }
        };
        // 循环创建线程并启动
        for (int i = 0; i < 100; i++) {
            new Thread(runnable).start();
        }
        try {
            // 使当前线程睡1秒钟
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 输出counter的值
        System.out.println(Counter.get());
    }
}