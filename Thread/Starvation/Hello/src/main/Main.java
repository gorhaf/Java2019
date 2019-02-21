package main;

import lab.Starvation;

public class Main {

    /**
     * 主入口
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {
        // 创建共享资源对象
        Starvation starvation = new Starvation();

        // 创建线程
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                // 访问共享资源
                starvation.task();
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                // 访问共享资源
                starvation.task();
            }
        });

        // 启动线程
        thread1.start();
        thread2.start();
    }
}