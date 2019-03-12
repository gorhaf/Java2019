package main;

import lab.SafeBuffer;

public class Main {

    /**
     * 主入口
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {
        // 创建缓冲区
        SafeBuffer safeBuffer = new SafeBuffer();

        // 实现了Runnable接口的匿名内部类
        Runnable putRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    // 存入数据
                    safeBuffer.put("hi");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable takeRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    // 获取数据
                    Object data = safeBuffer.take();
                    // 输出语句
                    System.out.println("data: " + data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        // 创建线程
        Thread putThread1 = new Thread(putRunnable);
        Thread putThread2 = new Thread(putRunnable);
        Thread putThread3 = new Thread(putRunnable);
        Thread takeThread1 = new Thread(takeRunnable);
        Thread takeThread2 = new Thread(takeRunnable);

        // 启动线程
        putThread1.start();
        putThread2.start();
        putThread3.start();
        try {
            // 使主线程睡1秒钟
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        takeThread1.start();
        takeThread2.start();
    }
}