package main;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    /**
     * 原子类
     */
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

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
                // 输出语句并计算i++
                System.out.println(Thread.currentThread().getName() + " --- " + atomicInteger.getAndIncrement());
            }
        };
        // 循环创建线程，模拟多个线程进行操作
        for (int i = 0; i < 10; i++) {
            // 创建线程并将任务传给线程，以及启动线程
            new Thread(runnable).start();
        }
    }
}