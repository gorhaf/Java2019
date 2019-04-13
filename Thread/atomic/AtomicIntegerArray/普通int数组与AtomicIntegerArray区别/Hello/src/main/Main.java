package main;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class Main {

    /**
     * 主入口
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {
        // 创建一个长度为10的int数组
        AtomicIntegerArray numbers = new AtomicIntegerArray(10);
        // 操作元素任务
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    // 使当前线程睡100毫秒
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 下标为0的元素自增
                numbers.getAndIncrement(0);
            }
        };
        // 循环创建线程并执行操作元素任务
        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }
        try {
            // 使当前线程睡1秒钟
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 输出下标为0的元素
        System.out.println(numbers.get(0));
    }
}