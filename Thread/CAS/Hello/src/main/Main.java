package main;

import lab.CompareAndSwap;

public class Main {

    /**
     * 主入口
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {
        // 创建CompareAndSwap对象
        CompareAndSwap cas = new CompareAndSwap();
        // 计算任务
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // 获取变量值
                int value = cas.get();
                // 给变量赋以新值并返回是否设置成功结果
                boolean success = cas.compareAndSet(value, value + 1);
                // 输出compareAndSet(int expectedValue, int newValue)方法返回值
                System.out.println(success);
            }
        };
        // 循环创建线程
        for (int i = 0; i < 10; i++) {
            // 创建线程并将任务传给线程，以及启动线程
            new Thread(runnable).start();
        }
        try {
            // 使当前线程睡1秒钟
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 输出变量值
        System.out.println(cas.get());
    }
}