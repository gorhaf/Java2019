package main;

import lab.Counter;

public class Main {

    /**
     * 主入口
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {
        // 计数任务
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // 递增计数器
                Counter.increment();
            }
        };
        // 循环创建线程并执行计数任务
        for (int i = 0; i < 100; i++) {
            new Thread(runnable).start();
        }
        try {
            // 使当前线程睡100毫秒
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 获取计数器当前值
        System.out.println(Counter.get());
    }
}